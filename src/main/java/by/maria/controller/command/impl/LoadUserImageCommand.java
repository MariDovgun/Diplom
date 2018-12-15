package by.maria.controller.command.impl;

import by.maria.entity.User;
import by.maria.service.ServiceFactory;
import by.maria.controller.command.Command;
import by.maria.controller.util.KeyHolder;
import by.maria.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class LoadUserImageCommand implements Command {



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            Part filePart = request.getPart(KeyHolder.UPFILE_KEY);
            String fileName = filePart.getSubmittedFileName();

            File upload = new File(request.getServletContext().getInitParameter(KeyHolder.USER_IMAGE_PATH));

            String prefix = fileName.substring(0, fileName.indexOf(KeyHolder.DOT_SEPARATOR));
            String suffix = fileName.substring(fileName.indexOf(KeyHolder.DOT_SEPARATOR));
            File file = File.createTempFile(prefix, suffix, upload);

            InputStream fileContent = filePart.getInputStream();
            Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            fileContent.close();

            int id= Integer.parseInt(request.getParameter(KeyHolder.USER_KEY));
            User user = ServiceFactory.getInstance().getUserService().getUserById(id);


            User currentUser = (User) request.getSession(true).getAttribute(KeyHolder.USER_KEY);

            if (currentUser.getLogin().equals(user.getLogin())) {
                request.getSession(true).setAttribute(KeyHolder.USER_KEY, currentUser);
            }

            PrintWriter writer=response.getWriter();
            writer.write(file.getName());

        } catch (ServiceException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
        }
    }
}
