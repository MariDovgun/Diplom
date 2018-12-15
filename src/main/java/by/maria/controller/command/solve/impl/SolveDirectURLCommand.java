package by.maria.controller.command.solve.impl;

import by.maria.controller.util.TaskPageHolder;
import by.maria.controller.command.solve.SolveCommand;
import by.maria.controller.util.KeyHolder;
import by.maria.entity.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SolveDirectURLCommand extends SolveCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Task task= (Task) request.getSession(true).getAttribute(KeyHolder.TASK_KEY);

        if (task==null || !task.getPage().equals(TaskPageHolder.DIRECT_URL_PAGE)){
            response.sendRedirect(KeyHolder.TASK_PATH);
            return;
        }

        String login=request.getParameter("login");
        String password=request.getParameter("password");

        PrintWriter writer=response.getWriter();

        if (login.equals("") || password.equals("")){
            writer.write("empty field");

        } else {
            request.getSession().setAttribute(KeyHolder.LOGIN_KEY,login);
            request.getSession().setAttribute(KeyHolder.PASSWORD_KEY,password);
            request.getSession().setAttribute("from task","true");

            writer.write("{\"redirect\":\"menu\"}");
        }
    }
}
