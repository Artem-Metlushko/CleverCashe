/*
package org.metlushko.cash.servlet;


import lombok.AllArgsConstructor;
import org.metlushko.cash.dao.impl.UserDao;
import org.metlushko.cash.entity.User;
import org.metlushko.cash.mapper.MapperUser;
import org.metlushko.cash.service.UserService;
import org.metlushko.cash.util.UuidWrapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


//@AllArgsConstructor
@WebServlet(urlPatterns = {"/api/"})
public class Controller extends HttpServlet {

    private static final String USER_FORM_JSP = "/userForm.jsp";
    private static final String USER_LIST_JSP = "/userList.jsp";
    public static final String LIST_USERS = "/api/list";

    //    private String userId;
//    private String firstName;
//    private String lastName;
//    private String surName;
//    private String email;
//    private String phoneNumber;
    @Override
    public void init() {

        userDAO.save(User.builder()
                .firstName("vaca")
                .lastName("Cola")
                .surName("surName")
                .phoneNumber("3487952")
                .email("qweqwe@com")
                .build());

        userDAO.save(User.builder()
                .firstName("vaca")
                .lastName("Cola")
                .surName("surName")
                .phoneNumber("3487952")
                .email("@asdsad.com")
                .build());

        userDAO.save(User.builder()
                .firstName("vaca")
                .lastName("Cola")
                .surName("surName")
                .phoneNumber("3487952")
                .email("qweqwe")
                .build());

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/";
        }
        switch (action) {
//            case "/insertUser" -> createUser(request, response);
            case "/new" -> form(request, response);
            case "/delete" -> deleteUser(request, response);
            case "/edit" -> showEditForm(request, response);
            case "/updateUser" -> updateUser(request, response);
            default -> listUser(request, response);
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        */
/*String parameter = request.getParameter("id");
        long id = Long.parseLong(parameter);
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");

        User build = User.builder()
                .userId(String.valueOf(id))
                .firstName(name)
                .address(address)
                .phoneNumber(phoneNumber)
                .build();

        userDAO.update(build, id);
        response.sendRedirect(LIST_USERS);*//*


    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        User getUser = userService.findById(id);
        request.setAttribute("user", getUser);
        request.getRequestDispatcher(USER_FORM_JSP).forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");

        userService.delete(id);

        response.sendRedirect(LIST_USERS);
    }

    private void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(USER_FORM_JSP).forward(request, response);
    }

*/
/*    private void createUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");


        User build = User.builder()
                .name(name)
                .address(address)
                .phoneNumber(phoneNumber)
                .build();

        userService.saveUser(build);
        response.sendRedirect(LIST_USERS);

    }*//*



    private void listUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<User> userList = userService.findAll();
        request.setAttribute("usersList", userList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(USER_LIST_JSP);
        requestDispatcher.forward(request, response);
    }
}
*/
