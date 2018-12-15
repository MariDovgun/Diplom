package by.maria.controller.command.solve;

import by.maria.controller.command.Command;
import by.maria.controller.util.KeyHolder;
import by.maria.entity.User;
import by.maria.exception.ServiceException;
import by.maria.service.ServiceFactory;
import by.maria.entity.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class SolveCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }

    protected void setComplete(HttpServletRequest request) throws ServiceException {

        Task task= (Task) request.getSession(true).getAttribute(KeyHolder.TASK_KEY);

        User user = (User) request.getSession().getAttribute(KeyHolder.USER_KEY);
        task.setComplete(true);
        LocalDateTime finishTime = LocalDateTime.now();
        long seconds = Math.abs(finishTime.until(task.getStartTime(), ChronoUnit.SECONDS));
        long minutes=seconds/60;
        seconds=seconds%60;
        ServiceFactory.getInstance().getTaskService().completeTask(user, task, minutes, seconds);
    }
}
