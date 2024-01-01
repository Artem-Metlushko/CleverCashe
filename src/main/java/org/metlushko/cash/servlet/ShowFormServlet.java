package org.metlushko.cash.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.metlushko.cash.config.ApplicationContextProvider;
import org.metlushko.cash.entity.User;

import org.metlushko.cash.service.UserService;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@WebServlet("/show")
public class ShowFormServlet extends HttpServlet {
    private static final String USER_FORM_JSP = "/WEB-INF/userForm.jsp";
    private UserService userService ;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();

        userService = context.getBean(UserService.class);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        String id = request.getParameter("id");
        User getUser = userService.findById(id);
        request.setAttribute("user", getUser);
        request.getRequestDispatcher(USER_FORM_JSP).forward(request, resp);
    }
}
