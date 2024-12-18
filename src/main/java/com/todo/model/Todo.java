package com.todo.model;

public class Todo {
    private int task_id;
    private int user_id;
    private String task;
    private boolean task_status;
    private String createdTime;

    public Todo(int task_id, int user_id, String task, boolean task_status, String createdTime) {
        this.task_id = task_id;
        this.user_id = user_id;
        this.task = task;
        this.task_status = task_status;
        this.createdTime = createdTime;
    }
    public Todo(Todo todo) {
        this.task_id = todo.getTask_id();
        this.user_id = todo.getUser_id();
        this.task = todo.getTask();
        this.task_status = todo.getTask_status();
        this.createdTime = todo.getCreatedTime();
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean getTask_status() {
        return task_status;
    }

    public void setTask_status(boolean task_status) {
        this.task_status = task_status;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
    @Override
    public String toString(){
        return String.format(
                "{ \"taskID\": %d, \"userID\": %d, \"description\": \"%s\", \"taskStatus\": %b, \"time\": \"%s\" }",
                task_id, user_id, task, task_status, createdTime
        );

    }
}
