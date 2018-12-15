package by.maria.service;

import by.maria.entity.TaskMarkPoint;
import by.maria.entity.TimeCountPoint;
import by.maria.entity.User;
import by.maria.entity.Task;
import by.maria.exception.DAOException;
import by.maria.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface TaskService {

    List<Task> getTasks(int level) throws ServiceException;

    String getTip(int taskId) throws DAOException;

    String getFreeTip(int taskId) throws ServiceException;

    void completeTask(User user, Task task, long minutes, long seconds) throws ServiceException;

    Map<String,Integer> getTaskResults(User user) throws ServiceException;

    List<TimeCountPoint> getTimeCountPoints() throws ServiceException;

    List<TaskMarkPoint> getTaskMarkPoints() throws ServiceException;
}
