package by.maria.controller.command.impl;

import by.maria.controller.command.Command;
import by.maria.exception.ServiceException;
import by.maria.service.ServiceFactory;
import by.maria.controller.util.KeyHolder;
import by.maria.entity.Task;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GetNewTaskCommand implements Command {

    private static final String GO_TASK="start?action=go_task";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Integer level= (Integer) request.getSession(true).getAttribute(KeyHolder.LEVEL_KEY);
        List<Task> tasks = (List<Task>) request.getSession(true).getAttribute(KeyHolder.TASKS_KEY);
        Task task= (Task) request.getSession(true).getAttribute(KeyHolder.TASK_KEY);

        try {;

            if (tasks.size() == 0) {
                level++;
                tasks = ServiceFactory.getInstance().getTaskService().getTasks(level);
            }
            if (task == null || task.isComplete()) {
                int index=ThreadLocalRandom.current().nextInt(0,tasks.size());
                task = tasks.get(index);

                tasks.remove(index);
                task.setStartTime(LocalDateTime.now());
                request.getSession().setAttribute("task",task);
                request.getSession().setAttribute(KeyHolder.LEVEL_KEY, level);
                request.getSession().setAttribute(KeyHolder.TASKS_KEY, tasks);

                response.sendRedirect(GO_TASK);

            } else {
                response.sendRedirect(GO_TASK);
            }

        } catch (ServiceException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
        }
    }
}
