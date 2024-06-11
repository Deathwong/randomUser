package com.jefrido.randomuser.controller;

import com.jefrido.randomuser.domain.User;
import com.jefrido.randomuser.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String redirectToUsers() {
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String getUsers(@RequestParam(required = false, defaultValue = "20") int results,
                           @RequestParam(required = false) String sortBy,
                           Model model) {

        List<User> users = userService.getUsers(results, sortBy);
        model.addAttribute("users", users);
        return "user-cards";
    }
}
