package by.maria.controller.filter;

import by.maria.entity.User;
import by.maria.exception.ServiceException;
import by.maria.service.ServiceFactory;
import by.maria.controller.util.KeyHolder;
import by.maria.entity.Task;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DirectURLFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String login = (String) request.getSession().getAttribute(KeyHolder.LOGIN_KEY);
        String password = (String) request.getSession().getAttribute(KeyHolder.PASSWORD_KEY);
        String fromTask = (String) request.getSession().getAttribute("from task");

        if(fromTask!=null){

            if (login == null || password == null) {

                try {

                    Task task = (Task) request.getSession(true).getAttribute(KeyHolder.TASK_KEY);
                    task.setComplete(true);
                    request.getSession().removeAttribute("from task");

                    //Warning! Shit code!
                    User user = (User) request.getSession().getAttribute(KeyHolder.USER_KEY);
                    task.setComplete(true);
                    LocalDateTime finishTime = LocalDateTime.now();
                    long seconds = Math.abs(finishTime.until(task.getStartTime(), ChronoUnit.SECONDS));
                    long minutes = seconds / 60;
                    seconds = seconds % 60;
                    ServiceFactory.getInstance().getTaskService().completeTask(user, task, minutes, seconds);

                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            }

            request.getSession().removeAttribute(KeyHolder.LOGIN_KEY);
            request.getSession().removeAttribute(KeyHolder.PASSWORD_KEY);

            filterChain.doFilter(request, response);

        } else {
            response.sendRedirect(KeyHolder.TASK_PATH);
        }
    }

    @Override
    public void destroy() {

    }
}
