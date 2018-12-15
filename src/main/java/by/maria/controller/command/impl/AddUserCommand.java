package by.maria.controller.command.impl;

import by.maria.controller.util.ReferenceEditor;
import by.maria.entity.User;
import by.maria.service.ServiceFactory;
import by.maria.service.UserService;
import by.maria.controller.command.Command;
import by.maria.controller.util.KeyHolder;
import by.maria.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUserCommand implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService service = factory.getUserService();


        User user = new User();
        user.setLogin(request.getParameter(KeyHolder.LOGIN_KEY));
        user.setPassword(request.getParameter(KeyHolder.PASSWORD_KEY));
        user.setName(request.getParameter(KeyHolder.NAME_KEY));
        user.setSurname(request.getParameter(KeyHolder.SURNAME_KEY));
        user.setRole(User.Role.valueOf(request.getParameter(KeyHolder.ROLE_KEY)));


        try {

            int userID=service.addUser(user);

        } catch (ServiceException e) {
            Logger logger= Logger.getRootLogger();
            logger.error(e.getMessage());

        } finally {
            response.sendRedirect(ReferenceEditor.getReference(request));
        }


    }
}
