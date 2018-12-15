package by.maria.controller.command.solve.impl;

import by.maria.controller.command.solve.SolveCommand;
import by.maria.controller.util.KeyHolder;
import by.maria.controller.util.TaskPageHolder;
import by.maria.entity.Task;
import by.maria.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SolveCommonDataCommand extends SolveCommand {

    private static final String CORRECT_LOGIN="user";
    private static final String CORRECT_PASSWORD="123";
    private static final String CORRECT_MSG="correct";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Task task= (Task) request.getSession(true).getAttribute(KeyHolder.TASK_KEY);

        if (task==null || !task.getPage().equals(TaskPageHolder.COMMON_DATA_PAGE)){
            response.setStatus(500);
            return;
        }

        String login=request.getParameter("login");
        String password=request.getParameter("password");
        String msg = CORRECT_MSG;

        try {

            msg += checkLogin(login);
            msg += checkPassword(password);

            if (msg.equals(CORRECT_MSG)) {
               setComplete(request);
            }

            PrintWriter writer=response.getWriter();
            writer.write(msg);

        } catch (ServiceException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
            response.setStatus(500);
        }
    }

    private String checkLogin(String login){
        if (login.equals(CORRECT_LOGIN)){
            return "";
        }
        else return "wrong login";
    }

    private String checkPassword(String password){
        if (password.equals(CORRECT_PASSWORD)){
            return "";
        }
        else return "wrong password";
    }
}
