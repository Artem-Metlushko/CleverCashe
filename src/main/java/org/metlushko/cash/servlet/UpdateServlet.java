package org.metlushko.cash.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.metlushko.cash.config.ApplicationContextProvider;
import org.metlushko.cash.entity.User;
import org.metlushko.cash.service.UserService;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@WebServlet("/updateUser")
public class UpdateServlet extends HttpServlet {

    public static final String LIST_USERS = "/list";

    private UserService userService ;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();

        userService = context.getBean(UserService.class);

    }

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

