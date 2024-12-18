package com.todo.web.taskOperations;

import com.todo.dao.SQLServerException;
import com.todo.dao.TodoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/updateTask")
public class UpdateTask extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        if (session == null || session.getAttribute("userID") == null){
            JSONObject jsonResponse = new JSONObject();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            jsonResponse.put("success","error");
            jsonResponse.put("message","User not loged in.");
            out.print(jsonResponse.toString());
            out.flush();
            return;
        }

        JSONObject jsonResponse = getJsonObject(request);
        out.print(jsonResponse.toString());
        out.flush();
    }

    private static JSONObject getJsonObject(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = reader.readLine())!=null){
            sb.append(line);
        }

        JSONObject jsonRequest = new JSONObject(sb.toString());
        int taskID = jsonRequest.getInt("taskID");
        boolean status = jsonRequest.getBoolean("taskStatus");

        TodoDAO todo = new TodoDAO();


        boolean isUpdated = todo.updateTaskStatus(taskID, status);

        JSONObject jsonResponse = new JSONObject();
        if (isUpdated) {
            jsonResponse.put("success", true);
            jsonResponse.put("message", "Task status updated successfully.");
        } else {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Task status not updated");
        }
        return jsonResponse;
    }
}