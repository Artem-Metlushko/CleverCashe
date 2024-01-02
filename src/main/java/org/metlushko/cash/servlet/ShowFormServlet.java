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

@WebServlet("/show")
public class ShowFormServlet extends HttpServlet {
    private static final String USER_FORM_JSP = "/WEB-INF/userForm.jsp";
    private UserService userService = FactoryService.getUserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        String id = request.getParameter("id");
        User getUser = userService.findById(id);
        request.setAttribute("user", getUser);
        request.getRequestDispatcher(USER_FORM_JSP).forward(request, resp);
    }
}
