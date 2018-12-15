package by.maria.controller.filter;

import by.maria.entity.User;
import by.maria.controller.util.KeyHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewTaskFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;

        if (request.getParameter(KeyHolder.ACTION_KEY).equals("go_new_task")) {

            User user = (User) request.getSession(true).getAttribute(KeyHolder.USER_KEY);

            if (user == null) {
                response.sendRedirect(KeyHolder.WELCOME_PATH);
            } else {
                filterChain.doFilter(request, response);
            }
        } else {
            filterChain.doFilter(request,response);
        }

    }

    @Override
    public void destroy() {

    }
}
