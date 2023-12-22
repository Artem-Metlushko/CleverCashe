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


@WebServlet("/insertUser")
public class InsertUserServlet extends HttpServlet {
    public static final String LIST_USERS = "/list";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserService userService = FactoryService.getUserService();

        UserDto userDto = UserDtoBuilder.builder()
                .firstName(req.getParameter("firstName"))
                .lastName(req.getParameter("lastName"))
                .surName(req.getParameter("surName"))
                .email(req.getParameter("email"))
                .phoneNumber(req.getParameter("phoneNumber"))
                .build();
        userService.save(userDto);

        resp.sendRedirect(LIST_USERS);

    }
}
