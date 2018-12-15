package by.maria.controller.command.solve.impl;

import by.maria.controller.command.solve.SolveCommand;
import by.maria.controller.util.KeyHolder;
import by.maria.controller.util.TaskPageHolder;
import by.maria.entity.Task;
import by.maria.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SolveCustomOptionCommand extends SolveCommand {

    private static final String DELETE_OPTION = "delete";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Task task= (Task) request.getSession(true).getAttribute(KeyHolder.TASK_KEY);

        if (task==null || !task.getPage().equals(TaskPageHolder.CUSTOM_OPTION_PAGE)){
            response.setStatus(500);
            return;
        }

        String option=request.getParameter("choose");

        try {

            if (option.toLowerCase().equals(DELETE_OPTION)) {
                setComplete(request);
            }

        } catch (ServiceException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
        }

        request.setAttribute("choose",option);
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/"+task.getPage());
        dispatcher.forward(request,response);
    }
}
