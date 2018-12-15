package by.maria.dao;

import by.maria.entity.User;
import by.maria.entity.Task;
import by.maria.exception.DAOException;

import java.util.List;

public interface TaskDAO {

    List<Task> getTasks() throws DAOException;

    List<Task> getTasks(int level) throws DAOException;

    String getTip(int tasId) throws DAOException;

    String getFreeTip(int taskId) throws DAOException;

    int getMaxLevel() throws DAOException;

    Task getFinishTask() throws DAOException;

    void completeTask(User user, Task task, String time) throws DAOException;

    int getAverageTime(Task task) throws DAOException;

    float getAverageMark(Task task) throws DAOException;

    List<Task> getCompleteTask(User user) throws DAOException;
}
