package by.maria.dao.impl;

import by.maria.dao.connection_pool.ConnectionPool;
import by.maria.entity.User;
import by.maria.dao.TaskDAO;
import by.maria.dao.connection_pool.ConnectionPoolException;
import by.maria.entity.Task;
import by.maria.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOImpl implements TaskDAO {

    private static final String LEVEL_KEY = "level";


    ConnectionPool connectionPool;

    // QUERIES //
    private static final String GET_AVERAGE_TIME_BY_TASK = "SELECT 3600*HOUR(CAST(AVG(time) as TIME))+60*MINUTE(CAST(AVG(time) as TIME))+SECOND(CAST(AVG(time) as TIME)) AS avg_time FROM complete where task_id=?";
    private static final String GET_AVERAGE_MARK_BY_TASK = "SELECT AVG(IF(use_paid_tip='TRUE',1,0)) AS avg_mark FROM complete where task_id=?";
    private static final String GET_FINISH_TASK = "SELECT * FROM task WHERE level=-1";
    private static final String GET_TASK_BY_LEVEL = "SELECT * FROM task WHERE level=?";
    private static final String GET_TASKS = "SELECT * FROM task WHERE level>0";
    private static final String GET_MAX_LEVEL = "SELECT MAX(level) AS level FROM task";
    private static final String GET_PAY_TIP_BY_TASK = "SELECT * FROM tip WHERE task_id=?";
    private static final String GET_FREE_TIP_BY_TASK = "SELECT * FROM free_tip WHERE task_id=?";
    private static final String ADD_COMPLETE = "INSERT INTO complete (user_id, task_id, time, use_tip, use_paid_tip) VALUES (?,?,?,?,?)";
    private static final String GET_COMPLETE_TASKS = "SELECT\n" +
            "task.id AS id,\n" +
            "task.title AS title,\n" +
            "complete.use_tip AS free_tip,\n" +
            "complete.use_paid_tip AS paid_tip,\n" +
            "(complete.task_id IS NOT NULL) AS complete\n" +
            "FROM task LEFT JOIN complete ON (task.id=complete.task_id AND complete.user_id=?) WHERE task.id>1";

    //KEYS//
    private static final String DESCRIPTION_KEY = "description";

    public TaskDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Task> getTasks() throws DAOException {

        List<Task> tasks = new ArrayList<>();

        try (
                Connection connection = connectionPool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(GET_TASKS);
                ResultSet rs = statement.executeQuery()
        ) {

            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setPage(rs.getString("page_name"));

                tasks.add(task);
            }
            return tasks;

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Task> getTasks(int level) throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        List<Task> tasks = new ArrayList<>();

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(GET_TASK_BY_LEVEL);
            statement.setInt(1, level);
            rs = statement.executeQuery();

            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setPage(rs.getString("page_name"));

                tasks.add(task);
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection, statement, rs);
        }

        return tasks;
    }

    @Override
    public String getTip(int tasId) throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        String text = "";

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(GET_PAY_TIP_BY_TASK);
            statement.setInt(1, tasId);
            rs = statement.executeQuery();

            while (rs.next()) {
                text = rs.getString(DESCRIPTION_KEY);
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection, statement, rs);
        }

        return text;
    }

    @Override
    public String getFreeTip(int tasId) throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        String text = "";

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(GET_FREE_TIP_BY_TASK);
            statement.setInt(1, tasId);
            rs = statement.executeQuery();

            while (rs.next()) {
                text = rs.getString(DESCRIPTION_KEY);
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection, statement, rs);
        }

        return text;
    }

    @Override
    public void completeTask(User user, Task task, String time) throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(ADD_COMPLETE);
            statement.setInt(1, user.getId());
            statement.setInt(2, task.getId());
            statement.setString(3, time);
            statement.setString(4, String.valueOf(task.isUseFreeTip()));
            statement.setString(5, String.valueOf(task.isUsePayTip()));
            statement.execute();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }
    }

    @Override
    public int getMaxLevel() throws DAOException {

        try (
                Connection connection = connectionPool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(GET_MAX_LEVEL);
                ResultSet rs = statement.executeQuery()
        ) {

            if (rs.next()) {
                return rs.getInt(LEVEL_KEY);
            } else {
                throw new DAOException("Have not a level");
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Task getFinishTask() throws DAOException {

        try (
                Connection connection = connectionPool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(GET_FINISH_TASK);
                ResultSet rs = statement.executeQuery();
        ) {

            Task task;

            if (rs.next()) {
                task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setPage(rs.getString("page_name"));

                return task;

            } else {
                throw new DAOException("Has no task");
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Task> getCompleteTask(User user) throws DAOException {

        PreparedStatement statement = null;
        ResultSet rs = null;

        try (Connection connection = connectionPool.takeConnection()) {

            List<Task> tasks = new ArrayList<>();

            statement = connection.prepareStatement(GET_COMPLETE_TASKS);
            statement.setInt(1, user.getId());
            rs = statement.executeQuery();

            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                if (rs.getBoolean("complete")) {
                    task.setComplete(true);
                    task.setUsePayTip(rs.getBoolean("paid_tip"));
                    task.setUseFreeTip(rs.getBoolean("free_tip"));
                }
                tasks.add(task);
            }

            return tasks;

        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public int getAverageTime(Task task) throws DAOException {

        try (
                Connection connection = connectionPool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(GET_AVERAGE_TIME_BY_TASK)
        ) {
            statement.setInt(1, task.getId());
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getInt("avg_time");
            } else {
                return 0;
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public float getAverageMark(Task task) throws DAOException {

        try (
                Connection connection = connectionPool.takeConnection();
                PreparedStatement statement = connection.prepareStatement(GET_AVERAGE_MARK_BY_TASK)
        ) {
            statement.setInt(1, task.getId());
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getFloat("avg_mark");
            } else {
                return 0;
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        }
    }
}

