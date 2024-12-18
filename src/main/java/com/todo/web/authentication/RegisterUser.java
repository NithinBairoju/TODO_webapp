package com.todo.web.authentication;

import com.todo.dao.SQLServerException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import com.todo.model.User;
import com.todo.dao.UserDAO;

@WebServlet("/signup")
public class RegisterUser extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException {
        doPost(request,response);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException {
        String userName = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(userName,email,password);
        UserDAO userMethods = new UserDAO();


        try {
            if(userMethods.insertUser(user)){
                HttpSession session = request.getSession();
                session.setAttribute("user",user);
                session.setAttribute("userName", user.getUserName());
                session.setAttribute("userEmail", user.getEmail());
                session.setAttribute("userID", userMethods.selectUser(user.getEmail()));
                response.sendRedirect("/TODO_webapp");
            }else {
                request.setAttribute("error_message", "user already exist");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            }
        } catch (SQLServerException e) {
            request.setAttribute("error_message", e.getMessage());
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }
}
