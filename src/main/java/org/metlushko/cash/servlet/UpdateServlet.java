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

@WebServlet("/updateUser")
public class UpdateServlet extends HttpServlet {

    private UserService userService = FactoryService.getUserService();
    public static final String LIST_USERS = "/list";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        String id = request.getParameter("id");
        String firstName = request.getParameter("firstName");
        String surName = request.getParameter("surName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");

        User build = User.builder()
                .userId(id)
                .firstName(firstName)
                .surName(surName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();


        userService.update(build);
        resp.sendRedirect(LIST_USERS);
    }
}

