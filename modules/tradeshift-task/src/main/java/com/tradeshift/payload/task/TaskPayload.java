package com.tradeshift.payload.task;


import com.tradeshift.commons.errorhandling.ErrorMessage;
import com.tradeshift.entity.Task;
import com.tradeshift.payload.AbstractPayload;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/30/15
 * Time: 3:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class TaskPayload extends AbstractPayload<Task,ErrorMessage> {

    private Task task;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
