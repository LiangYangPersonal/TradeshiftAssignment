package com.tradeshift.payload.task;

import com.tradeshift.entity.Task;
import com.tradeshift.payload.AbstractPayload;
import com.tradeshift.result.TaskResult;
import com.tradeshift.result.TasksResult;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/31/15
 * Time: 1:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class GetTaskByTaskIdPayload extends AbstractPayload<Integer,TaskResult> {

    private Task task;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    private Integer taskId;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
}
