package org.metlushko.cash.servlet;

import org.metlushko.cash.dto.UserDto;
import org.metlushko.cash.dto.UserDtoBuilder;
import org.metlushko.cash.entity.User;
import org.metlushko.cash.factory.FactoryService;
import org.metlushko.cash.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/go")
public class CreateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserService userService = FactoryService.getUserService();

        UserDto userDto = UserDtoBuilder.builder()
                .firstName(req.getParameter("firstname"))
                .lastName(req.getParameter("lastName"))
                .surName(req.getParameter("surName"))
                .email(req.getParameter("email"))
                .phoneNumber(req.getParameter("phoneNumber"))
                .build();
        User save = userService.save(userDto);
        String name = save.getUserId();

        req.setAttribute("user", save);

        try (PrintWriter out = resp.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Hello World!</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Hello " + name + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
