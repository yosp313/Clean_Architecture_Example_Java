package com.youssef_ayman.clean_architecture_example.Presentation;

import com.youssef_ayman.clean_architecture_example.Application.Services.UserService;
import com.youssef_ayman.clean_architecture_example.Domain.Entities.User;
import com.youssef_ayman.clean_architecture_example.Domain.Repositories.UserRepo;
import com.youssef_ayman.clean_architecture_example.Infrastructure.InMemoryRepo;
import com.youssef_ayman.clean_architecture_example.Infrastructure.MySqlRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        UserRepo userRepo = new MySqlRepo();
        userService = new UserService(userRepo);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        User user = userService.get(name);

        if (user == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }else {
            resp.getWriter().write("User: " + user.getName() + " Age: " + user.getAge() + " Job: " + user.getJob());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        String job = req.getParameter("job");

        userService.post(name, age, job);

        resp.getWriter().write("User posted!");
    }
}
