package by.maria.controller.command.impl;

import by.maria.controller.command.Command;
import by.maria.controller.util.KeyHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        session.setAttribute(KeyHolder.LANG_KEY, request.getParameter(KeyHolder.LANG_KEY));
    }
}
