package com.todo.web;

import com.todo.dao.SQLServerException;
import com.todo.dao.UserDAO;
import com.todo.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/UserProfile")
public class UserProfile extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            doPost(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("userEmail");
        User user = (User) session.getAttribute("user");
        UserDAO userDao = new UserDAO();

        int userID =  (int) session.getAttribute("userID");

        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        switch (action){
            case "updateUserName":

                String userName = (String) request.getParameter("userName");
                boolean isUpdated = userDao.updateUserName(userName,userID,user);
                if (isUpdated){
                    session.setAttribute("userName",userName);
                    request.setAttribute("successMessage","Username updated successfully!");
                    request.getRequestDispatcher("UserAccount.jsp").forward(request,response);
                }else
                {
                    request.setAttribute("errorMessage","Failed update username!");
                    request.getRequestDispatcher("UserAccount.jsp").forward(request,response);
                }
                break;
            case "updateMail":

                 String userMail = (String) request.getParameter("userMail");
                try {
                    boolean isUpdatedEmail = userDao.updateMail(userMail, userID, user);
                    if (isUpdatedEmail) {
                        session.setAttribute("userEmail", userMail);
                        request.setAttribute("successMessage", "Email updated successfully!");
                        request.getRequestDispatcher("UserAccount.jsp").forward(request, response);
                    } else {
                        request.setAttribute("errorMessage", "Failed update Email!");
                        request.getRequestDispatcher("UserAccount.jsp").forward(request, response);
                    }
                } catch (SQLServerException e) {
                    request.setAttribute("errorMessage", e.getMessage());
                    request.getRequestDispatcher("UserAccount.jsp").forward(request, response);
                }
                break;
            case "updatePassword":

                String userPassword = (String) request.getParameter("userPassword");
                try {
                    isUpdated = userDao.updatePassword(userPassword, userID, user);
                    if (isUpdated) {
                        request.setAttribute("successMessage", "Email updated successfully!");
                        request.getRequestDispatcher("UserAccount.jsp").forward(request, response);
                    } else {
                        request.setAttribute("errorMessage", "Failed update Email!");
                        request.getRequestDispatcher("UserAccount.jsp").forward(request, response);
                    }
                }catch (SQLServerException e){
                    request.setAttribute("errorMessage", "Failed update Email!");
                    request.getRequestDispatcher("UserAccount.jsp").forward(request, response);
                }
                break;
            case "DeleteAccount":

                isUpdated = userDao.deleteUser(userID,user);
                if (isUpdated){
                    session.invalidate();
                    response.sendRedirect("login.jsp");
                }else
                {
                    request.setAttribute("errorMessage","Failed delete account!");
                    request.getRequestDispatcher("UserAccount.jsp").forward(request,response);
                }
                break;
            default:
                request.setAttribute("errorMessage","Something went wrong!");
                request.getRequestDispatcher("UserAccount.jsp").forward(request,response);
        }
    }
}
