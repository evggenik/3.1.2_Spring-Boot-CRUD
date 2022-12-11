package me.learning.springbootcrud.controller;

import me.learning.springbootcrud.service.UserService;
import me.learning.springbootcrud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/add")
    public String add(User user) {
        return "add";
    }
    @PostMapping("/add")
    public String addUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
    @GetMapping("/edit/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute(user);
        return "edit";
    }
    @PostMapping("/edit")
    public String updateUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }
}
