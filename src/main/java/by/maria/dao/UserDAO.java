package by.maria.dao;

import by.maria.entity.User;
import by.maria.exception.DAOException;

import java.util.List;

public interface UserDAO {

    User authorUser(String login, String password) throws DAOException;

    User getUserById(int id) throws DAOException;

    List<User> getUsers() throws DAOException;

    int addUser(User user) throws DAOException;

    boolean deleteUser(int userID) throws DAOException;

}
