package by.maria.service.impl;

import by.maria.dao.DAOFactory;
import by.maria.entity.User;
import by.maria.exception.ServiceException;
import by.maria.service.UserService;
import by.maria.service.validate.GeneralValidator;
import by.maria.service.validate.UserValidator;
import by.maria.dao.UserDAO;
import by.maria.exception.DAOException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static final String NOT_VALID_MSG="Data is not valid";

    @Override
    public User authorizeUser(String login, String password) throws ServiceException {

        if(!UserValidator.checkField(login) && UserValidator.checkField(password)){
            throw new ServiceException(NOT_VALID_MSG);
        }

        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();

        User user = null;

        try {

            user = userDAO.authorUser(login, password);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return user;
    }


    @Override
    public List<User> getUsers() throws ServiceException {

        UserDAO dao = DAOFactory.getInstance().getUserDAO();

        List<User> users = new ArrayList<>();

        try {

            users = dao.getUsers();

        } catch (DAOException e) {
            throw new ServiceException(e);

        }
        return users;
    }


    @Override
    public void editUser(User user) throws ServiceException {

        if(!UserValidator.checkUserEditing(user)){
            Logger.getLogger(getClass()).warn(NOT_VALID_MSG);
            return;
        }

        UserDAO dao = DAOFactory.getInstance().getUserDAO();

    }


    @Override
    public int addUser(User user) throws ServiceException {

        if(!UserValidator.checkUserAdding(user)){
            Logger.getLogger(getClass()).warn(NOT_VALID_MSG);
            throw new ServiceException(NOT_VALID_MSG);
        }

        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();

        try {

            return userDAO.addUser(user);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public boolean deleteUser(int userID) throws ServiceException {

        UserDAO dao = DAOFactory.getInstance().getUserDAO();
        boolean isDeleted = false;

        if(!GeneralValidator.checkId(userID)){
            Logger.getLogger(getClass()).warn(NOT_VALID_MSG);
            return isDeleted;
        }

        try {

            isDeleted = dao.deleteUser(userID);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return isDeleted;
    }

    @Override
    public User getUserById(int id) throws ServiceException {

        if (!GeneralValidator.checkId(id)){
            throw new ServiceException(NOT_VALID_MSG);
        }

        try {

            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            User user = userDAO.getUserById(id);

            return user;

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
