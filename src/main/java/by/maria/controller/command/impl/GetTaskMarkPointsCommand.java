package by.maria.controller.command.impl;

import by.maria.controller.command.Command;
import by.maria.entity.TaskMarkPoint;
import by.maria.exception.ServiceException;
import by.maria.service.ServiceFactory;
import by.maria.service.TaskService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetTaskMarkPointsCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        TaskService taskService=ServiceFactory.getInstance().getTaskService();

        try{

            List<TaskMarkPoint> points=taskService.getTaskMarkPoints();
            Gson gson=new Gson();
            String json=gson.toJson(points);

            PrintWriter writer=response.getWriter();
            writer.write(json);

        } catch (ServiceException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
        }

    }
}
