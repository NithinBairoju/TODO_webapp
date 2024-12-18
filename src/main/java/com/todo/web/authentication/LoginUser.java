package com.todo.web.authentication;


import com.todo.dao.SQLServerException;
import com.todo.dao.UserNotFound;
import com.todo.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.todo.dao.UserLogin;
import java.io.IOException;

@WebServlet("/login")
public class LoginUser extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException {
        doPost(request,response);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserLogin login = new UserLogin();

        try {
            User user =login.checkUserExist(email, password);
            if ( user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("userName", user.getUserName());
                session.setAttribute("userEmail", user.getEmail());
                session.setAttribute("userID", user.getUser_id());
                response.sendRedirect("/TODO_webapp");
            } else {
                request.setAttribute("error_message", "Invalid username or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }catch (SQLServerException e){
            request.setAttribute("error_message", e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }catch (UserNotFound e){
            request.setAttribute("error_message", e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
