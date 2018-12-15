package by.maria.controller.command.impl;

import by.maria.entity.User;
import by.maria.controller.command.Command;
import by.maria.controller.util.KeyHolder;
import by.maria.exception.ServiceException;
import by.maria.service.ServiceFactory;
import by.maria.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class SignUpUserCommand implements Command {


    private static final String SIGN_UP_ERROR="error";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService service = factory.getUserService();

        User user = new User();
        user.setLogin(request.getParameter(KeyHolder.LOGIN_KEY));
        user.setPassword(request.getParameter(KeyHolder.PASSWORD_KEY));
        user.setName(request.getParameter(KeyHolder.NAME_KEY));
        user.setSurname(request.getParameter(KeyHolder.SURNAME_KEY));
        user.setGroup(request.getParameter(KeyHolder.GROUP_KEY));
        user.setRole(User.Role.USER);

        try {
            int userID = service.addUser(user);
            user.setId(userID);


            if (userID != -1) {
                HttpSession session = request.getSession(true);
                session.setAttribute(KeyHolder.USER_KEY, user);
            } else {
                PrintWriter writer=response.getWriter();
                writer.write(SIGN_UP_ERROR);
            }

        } catch (ServiceException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
        }
    }
}
