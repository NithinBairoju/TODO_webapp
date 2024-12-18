package com.todo.web.taskOperations;

import com.google.gson.Gson;
import com.todo.model.Todo;
import com.todo.dao.TodoDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONArray;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/FetchTask")
public class Fetchtask extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session == null || session.getAttribute("userID") == null){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"status\": \"error\", \"message\": \"User not logged in\"}");
            return;
        }

        int userID = (Integer) session.getAttribute("userID");
        TodoDAO todoDAO = new TodoDAO();
        ArrayList<Todo> todoList = todoDAO.getTodoList(userID);
        if (todoList.isEmpty()){
            response.getWriter().write("{\"status\": \"todoIsEmpty\", \"message\": \"list is empty\"}");
            return;
        }
        ArrayList<String> taskList = new ArrayList<>();
        for (Todo task: todoList){
        String taskStr = task.toString();
        taskList.add(taskStr);
        }

        response.setContentType("application/json");
        JSONArray jsonTasks = new JSONArray(taskList);
        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(taskList));
        out.flush();

    }
}
