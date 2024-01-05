package org.metlushko.cash.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.metlushko.cash.config.ApplicationContextProvider;
import org.metlushko.cash.service.UserService;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    public static final String LIST_USERS = "/list";

    private UserService userService ;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();

        userService = context.getBean(UserService.class);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doDelete(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        userService.delete(id);

        resp.sendRedirect(LIST_USERS);

    }
}
