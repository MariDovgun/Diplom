package by.maria.service;

import by.maria.entity.User;
import by.maria.exception.ServiceException;

import java.util.List;

public interface UserService {

    User authorizeUser(String login, String password) throws  ServiceException;

    User getUserById(int id) throws ServiceException;

    List<User> getUsers() throws ServiceException;

    void editUser(User user) throws ServiceException;

    boolean deleteUser(int userID) throws ServiceException;

    int addUser(User user) throws ServiceException;

}
