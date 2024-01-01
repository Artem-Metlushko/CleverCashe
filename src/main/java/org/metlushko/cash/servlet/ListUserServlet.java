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
import java.util.List;

@WebServlet("/list")
public class ListUserServlet extends HttpServlet {

    private static final String USER_LIST_JSP = "/WEB-INF/userList.jsp";
    private UserService userService ;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();

        userService = context.getBean(UserService.class);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> userList = userService.findAll();

        req.setAttribute("usersList", userList);
        req.getRequestDispatcher(USER_LIST_JSP).forward(req, resp);
    }

}
