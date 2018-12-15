package by.maria.controller.command.solve.impl;

import by.maria.controller.util.TaskPageHolder;
import by.maria.exception.ServiceException;
import by.maria.controller.command.solve.SolveCommand;
import by.maria.controller.util.KeyHolder;
import by.maria.entity.Task;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SolveAddButtonCommand extends SolveCommand {

    private static final String GO_TASK="start?action=go_new_task";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Task task= (Task) request.getSession(true).getAttribute(KeyHolder.TASK_KEY);

        if (task==null || !task.getPage().equals(TaskPageHolder.ADD_BUTTON_PAGE)){
            response.setStatus(500);
            return;
        }

        try {
            setComplete(request);
            response.sendRedirect(GO_TASK);
        } catch (ServiceException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
        }
    }
}
