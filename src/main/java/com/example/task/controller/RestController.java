package com.example.task.controller;

import com.example.task.entity.User;
import com.example.task.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(path = "/api")
public class RestController {
    private UserService userService;

    @Autowired
    public RestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public RedirectView showCreateUserForm() {
        return new RedirectView("/index.html");
    }

    @GetMapping("/createUser")
    public RedirectView create() {
        return new RedirectView("/create.html");
    }

    @GetMapping("/updateUser")
    public RedirectView update() {
        return new RedirectView("/update.html");
    }

    @GetMapping("/deleteUser")
    public RedirectView delete() {
        return new RedirectView("/delete.html");
    }

    @GetMapping("/getUsers")
    public List<User> findAll() {
        return userService.findAll();
    }

    @Transactional
    @PostMapping("/createUser")
    public RedirectView createUser(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName,
                                   @RequestParam("age") int age,
                                   @RequestParam("email") String email) {
        User user = new User(firstName, lastName, age, email);
        userService.save(user);
        return new RedirectView("/index.html");
    }

    @Transactional
    @PostMapping("/updateUser")
    public RedirectView updateUser(@RequestParam("id") Long id,
                                   @RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName,
                                   @RequestParam("age") int age,
                                   @RequestParam("email") String email) {
        User byId = userService.findById(id);
        byId = userService.newPropertiesForExistingUser(byId, id, firstName, lastName, age, email);
        userService.save(byId);
        return new RedirectView("/index.html");
    }

    @Transactional
    @PostMapping("/deleteUser")
    public RedirectView deleteEmployee(@RequestParam Long id) {
        User user = userService.findById(id);
        if (user == null) {
            throw new RuntimeException("Employee id not found - " + id);
        }
        userService.deleteUser(id);
        return new RedirectView("/index.html");
    }
}
