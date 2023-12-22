package org.metlushko.cash.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final String USER_FORM_JSP = "/WEB-INF/userForm.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(USER_FORM_JSP).forward(req, resp);

    }
}
