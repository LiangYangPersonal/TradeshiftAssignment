package com.tradeshift.result;

import com.tradeshift.commons.errorhandling.ErrorMessage;
import com.tradeshift.entity.Task;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/30/15
 * Time: 6:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class TasksResult extends ErrorMessage {

    private List<Task> tasks;

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
