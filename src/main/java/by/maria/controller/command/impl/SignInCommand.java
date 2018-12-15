package by.maria.controller.command.impl;

import by.maria.entity.User;
import by.maria.service.ServiceFactory;
import by.maria.service.UserService;
import by.maria.controller.command.Command;
import by.maria.controller.util.KeyHolder;
import by.maria.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class SignInCommand implements Command {

    private static final String ERROR_REFER="/sign_in_error";
    private static final String SUCCESS="success";
    private static final String FAILURE="failure";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {

            ServiceFactory factory= ServiceFactory.getInstance();
            UserService service=factory.getUserService();

            User user=service.authorizeUser(request.getParameter(KeyHolder.LOGIN_KEY),request.getParameter(KeyHolder.PASSWORD_KEY));
            HttpSession session=request.getSession();
            if (user!=null){
                session.setAttribute(KeyHolder.USER_KEY,user);
                PrintWriter writer=response.getWriter();
                writer.write(SUCCESS);
            } else {
                PrintWriter writer=response.getWriter();
                writer.write(FAILURE);
            }

        } catch (ServiceException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
        }
    }
}
