//package com.example.quizwebsite.controller;
//
//import com.example.quizwebsite.model.User;
//import com.example.quizwebsite.service.UserDAO;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
//
//@WebServlet(value = "/listuser")
//public class AdminServlet extends HttpServlet {
//    private UserDAO userDAO;
//
//    @Override
//    public void init() throws ServletException {
//        super.init();
//        userDAO = new UserDAO();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("admin/admin.jsp").forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, List<User> {
//        String action = req.getParameter("action");
//        if (action == null) {
//            action = "";
//            try {
//                switch (action) {
//                    case "listUser":
//                        listUser(req, resp);
//                        break;
//                    case "deleteUser":
//                        deleteUser(req, resp);
//                        break;
//                }
//            } catch (SQLException ex) {
//                throw new ServletException(ex);
//            }
//        }
//    }
//    private void listUser(HttpServletRequest request, HttpServletResponse response)
//            throws IOException, ServletException {
//        List<User> listUser = userDAO.selectAllUsers();
//        request.setAttribute("listUser", listUser);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
//        dispatcher.forward(request, response);
//    }
//}
