package by.maria.controller.filter;

import by.maria.controller.util.KeyHolder;
import by.maria.entity.User;
import by.maria.entity.Task;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StartFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        Integer level= (Integer) request.getSession().getAttribute(KeyHolder.LEVEL_KEY);
        List<Task> tasks= (List<Task>) request.getSession().getAttribute(KeyHolder.TASKS_KEY);
        User user = (User) request.getSession().getAttribute(KeyHolder.USER_KEY);

//        if(user == null){
//            try {
//                user=DAOFactory.getInstance().getUserDAO().authorUser("nick","nick");
//                request.getSession().setAttribute(KeyHolder.USER_KEY,user);
//            } catch (DAOException e) {
//                e.printStackTrace();
//            }
//        }

        if (level==null){
            request.getSession().setAttribute(KeyHolder.LEVEL_KEY,Integer.valueOf(0));
        }

        if (tasks==null) {
            tasks=new ArrayList<>();
            request.getSession().setAttribute(KeyHolder.TASKS_KEY,tasks);
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
