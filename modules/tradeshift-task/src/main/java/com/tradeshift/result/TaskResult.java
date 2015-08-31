package com.tradeshift.result;

import com.tradeshift.commons.errorhandling.ErrorMessage;
import com.tradeshift.entity.Task;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/31/15
 * Time: 1:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class TaskResult extends ErrorMessage {
    private Task task;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
