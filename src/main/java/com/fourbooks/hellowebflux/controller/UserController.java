package com.fourbooks.hellowebflux.controller;

import com.fourbooks.hellowebflux.domain.User;
import com.fourbooks.hellowebflux.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String index() {
        return "index";
    }

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("name", user.getName());
            model.addAttribute("age", user.getAge());
        } else {
            model.addAttribute("message", "회원이 존재하지 않습니다.");
        }
        return "user";
    }
}

