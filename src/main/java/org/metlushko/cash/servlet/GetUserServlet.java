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

@WebServlet("/get")
public class GetUserServlet extends HttpServlet {
    private UserService userService = FactoryService.getUserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = userService.findById(req.getParameter("id"));
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/userForm.jsp").forward(req, resp);

    }
}
