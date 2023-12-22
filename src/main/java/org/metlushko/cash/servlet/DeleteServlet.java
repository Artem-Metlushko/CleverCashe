package org.metlushko.cash.servlet;

import org.metlushko.cash.factory.FactoryService;
import org.metlushko.cash.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = FactoryService.getUserService();
        userService.delete(req.getParameter("id"));

        req.setAttribute("deleted", "true");
    }
}
