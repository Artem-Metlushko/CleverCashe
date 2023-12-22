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
import java.io.PrintWriter;

@WebServlet("/read")
public class ReadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = FactoryService.getUserService();
        User user = userService.findById(req.getParameter("id"));

        resp.setHeader("golova", "golova");

        if(user != null) {
            req.setAttribute("user", user);
            try (PrintWriter out = resp.getWriter()) {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Hello World!</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>User " + user.getUserId() + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            try (PrintWriter out = resp.getWriter()) {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Hello World!</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>User not found</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }
}
