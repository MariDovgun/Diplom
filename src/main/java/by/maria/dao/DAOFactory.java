package by.maria.dao;

import by.maria.dao.connection_pool.ConnectionPool;
import by.maria.dao.impl.TaskDAOImpl;
import by.maria.dao.impl.UserDAOImpl;


public class DAOFactory {

    private static DAOFactory ourInstance = new DAOFactory();
    private UserDAO userDAO;
    private TaskDAO taskDAO;
    private ConnectionPool connectionPool= ConnectionPool.getInstance();

    private DAOFactory() {
        userDAO = new UserDAOImpl(connectionPool);
        taskDAO=new TaskDAOImpl(connectionPool);
    }

    public static DAOFactory getInstance() {
        return ourInstance;
    }


    public UserDAO getUserDAO() {
        return userDAO;
    }

    public TaskDAO getTaskDAO() {
        return taskDAO;
    }
}
