package org.metlushko.cash.servlet;

import org.metlushko.cash.entity.User;
import org.metlushko.cash.factory.FactoryService;
import org.metlushko.cash.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/list")
public class ListUserServlet extends HttpServlet {
    private UserService userService = FactoryService.getUserService();
    private static final String USER_LIST_JSP = "/WEB-INF/userList.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> userList = userService.findAll();
        req.setAttribute("usersList", userList);
        req.getRequestDispatcher(USER_LIST_JSP).forward(req, resp);
    }

}
