package com.tradeshift.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/30/15
 * Time: 9:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class User {
    @JsonProperty("userId")
    private int id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<Task> tasks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
