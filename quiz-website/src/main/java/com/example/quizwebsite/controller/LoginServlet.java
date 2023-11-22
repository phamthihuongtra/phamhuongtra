package com.example.quizwebsite.controller;

import com.example.quizwebsite.model.User;
import com.example.quizwebsite.service.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "logout":
                logOut(request, response);
                break;
            default:
                request.getRequestDispatcher("/login/login.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "login":
                try {
                    loginToHomePage(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;

            default:
                break;
        }
    }

    public void loginToHomePage(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean foundUser = false;

        if (email.trim().isEmpty() || password.trim().isEmpty()) {
            response.sendRedirect("login.jsp?error=invalid");
        } else {
            List<User> userList = userDAO.getAllUsers();
            for (User user : userList) {
                if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                    foundUser = true;
                    if (user.getPermission() == 1) {
                        request.getRequestDispatcher("home/teacher_home.jsp").forward(request, response);
                    } else if (user.getPermission() == 2) {
                        request.getRequestDispatcher("home/student_home.jsp").forward(request, response);
                    }
                    break;
                }
            }

            if (!foundUser) {
                String errorMessage = "Invalid email or password. Please try again.";
                request.setAttribute("errorMessage", errorMessage);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
                dispatcher.forward(request, response);
            }
        }
    }


    public void logOut(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        try {
            response.sendRedirect("/login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}