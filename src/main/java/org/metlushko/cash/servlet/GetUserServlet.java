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

@WebServlet("/get")
public class GetUserServlet extends HttpServlet {
    private UserService userService ;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();

        userService = context.getBean(UserService.class);

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = userService.findById(req.getParameter("id"));
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/userForm.jsp").forward(req, resp);

    }
}
