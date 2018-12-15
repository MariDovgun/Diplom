package by.maria.controller.command.impl;

import by.maria.controller.command.Command;
import by.maria.entity.Task;
import by.maria.exception.ServiceException;
import by.maria.service.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetFreeTipCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Task task= (Task) request.getSession().getAttribute("task");

        String tipText= null;
        try {
            tipText = ServiceFactory.getInstance().getTaskService().getFreeTip(task.getId());

            if (!task.isComplete()) {
                task.setUseFreeTip(true);
            }

            PrintWriter writer=response.getWriter();
            writer.write(tipText);
        } catch (ServiceException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
            response.setStatus(501);
        }
    }
}
