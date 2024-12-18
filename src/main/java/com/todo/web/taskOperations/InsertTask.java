package com.todo.web.taskOperations;

import com.todo.dao.SQLServerException;
import com.todo.dao.TodoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

@WebServlet("/insertTodo")
public class InsertTask extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();
        PrintWriter out = response.getWriter();

        if (session == null || session.getAttribute("userID") == null){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            jsonResponse.put("status","failed");
            jsonResponse.put("message","User not loged in.");
            out.print(jsonResponse.toString());
            out.flush();
            return;
        }

        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = reader.readLine())!=null){
            sb.append(line);
        }

        TodoDAO todo = new TodoDAO();
        int userID = (Integer) session.getAttribute("userID");

        JSONObject jsonRequest = new JSONObject(sb.toString());
        String task = jsonRequest.getString("task");

        try {
            int taskId =  todo.insertTask(userID, task);
            jsonResponse.put("status","success");
            jsonResponse.put("message","successfully updated the todo.");
            jsonResponse.put("taskID",taskId);
        }catch(SQLServerException e){
            jsonResponse.put("status","failed");
            jsonResponse.put("message","failed to update the todo.");
            jsonResponse.put("taskID",-1);
        }

        out.print(jsonResponse);
        out.flush();
    }


}
