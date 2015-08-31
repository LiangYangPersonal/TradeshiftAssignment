package com.tradeshift.dao;

import com.tradeshift.commons.dao.TaskBaseDAO;
import com.tradeshift.entity.Task;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/30/15
 * Time: 1:33 PM
 * To change this template use File | Settings | File Templates.
 */
public interface TaskDAO extends TaskBaseDAO<Task> {
    int assginToUser(int userId, int taskId);
    int changeStatus(String status, int taskId);
    List<Task> getTasks(int userId);
    Task getTask(int taskId);
}
