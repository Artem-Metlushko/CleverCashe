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

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = FactoryService.getUserService();

        User user = userService.findById(req.getParameter("id"));
        userService.update(user);

        req.setAttribute("user", user);
    }
}
