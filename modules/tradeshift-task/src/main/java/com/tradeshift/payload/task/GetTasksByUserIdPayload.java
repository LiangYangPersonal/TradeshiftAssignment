package com.tradeshift.payload.task;

import com.tradeshift.commons.errorhandling.ErrorMessage;
import com.tradeshift.entity.Task;
import com.tradeshift.payload.AbstractPayload;
import com.tradeshift.result.TasksResult;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/30/15
 * Time: 5:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class GetTasksByUserIdPayload extends AbstractPayload<Integer,TasksResult> {

    private int userId;

    private List<Task> tasks;

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
