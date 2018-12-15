package by.maria.service.impl;

import by.maria.dao.DAOFactory;
import by.maria.dao.TaskDAO;
import by.maria.entity.TaskMarkPoint;
import by.maria.entity.TimeCountPoint;
import by.maria.entity.User;
import by.maria.exception.ServiceException;
import by.maria.service.TaskService;
import by.maria.entity.Task;
import by.maria.exception.DAOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskServiceImpl implements TaskService {

    private static final int PER_COMPLETE = 5;
    private static final int PER_TIP = 3;

    @Override
    public List<Task> getTasks(int level) throws ServiceException {

        List<Task> tasks = new ArrayList<>();
        TaskDAO taskDAO = DAOFactory.getInstance().getTaskDAO();

        try {
            if (level > taskDAO.getMaxLevel()) {
                tasks.add(taskDAO.getFinishTask());
            } else {
                tasks = DAOFactory.getInstance().getTaskDAO().getTasks(level);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return tasks;
    }

    @Override
    public String getTip(int taskId) throws DAOException {

        return DAOFactory.getInstance().getTaskDAO().getTip(taskId);
    }

    @Override
    public String getFreeTip(int taskId) throws ServiceException {

        try {
            return DAOFactory.getInstance().getTaskDAO().getFreeTip(taskId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void completeTask(User user, Task task, long minutes, long seconds) throws ServiceException {

        String time = "00:" + parseString(minutes) + ":" + parseString(seconds);
        try {
            DAOFactory.getInstance().getTaskDAO().completeTask(user, task, time);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private static String parseString(long time) {
        if (time / 10 == 0) {
            return "0" + time;
        } else {
            return String.valueOf(time);
        }
    }

    @Override
    public Map<String, Integer> getTaskResults(User user) throws ServiceException {

        TaskDAO taskDAO = DAOFactory.getInstance().getTaskDAO();
        Map<String, Integer> taskResults = new HashMap<>();

        try {

            List<Task> tasks = taskDAO.getCompleteTask(user);

            for (Task task : tasks) {
                int mark;
                if (task.isComplete()) {
                    mark = PER_COMPLETE - (task.isUsePayTip() ? PER_TIP : 0);
                } else {
                    mark = 0;
                }
                taskResults.put(task.getTitle(), mark);
            }

            return taskResults;

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<TimeCountPoint> getTimeCountPoints() throws ServiceException {

        List<TimeCountPoint> points = new ArrayList<>();
        TaskDAO taskDAO = DAOFactory.getInstance().getTaskDAO();
        int count = 0;
        int time = 0;

        try {
            List<Task> tasks = taskDAO.getTasks();
            for (Task task : tasks) {

                TimeCountPoint point = new TimeCountPoint();

                time += taskDAO.getAverageTime(task);
                point.time = time;
                point.count = ++count;

                points.add(point);
            }

            return points;

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<TaskMarkPoint> getTaskMarkPoints() throws ServiceException {

        List<TaskMarkPoint> points = new ArrayList<>();
        TaskDAO taskDAO = DAOFactory.getInstance().getTaskDAO();

        try {

            List<Task> tasks = taskDAO.getTasks();
            for (Task task : tasks) {

                TaskMarkPoint point = new TaskMarkPoint();

                point.task = task.getTitle();
                point.mark = (taskDAO.getAverageMark(task) * PER_TIP) + (PER_COMPLETE - PER_TIP);

                points.add(point);
            }

            return points;

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
