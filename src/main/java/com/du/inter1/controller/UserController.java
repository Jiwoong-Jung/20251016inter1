package com.du.inter1.controller;

import com.du.inter1.entity.User;
import com.du.inter1.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String registerForm() {
        return "users/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
        if (userRepository.findByUsername(username) != null) {
            redirectAttributes.addFlashAttribute("error", "이미 존재하는 사용자입니다.");
            return "redirect:/users/register";
        }

        User newUser = new User(username, password);
        userRepository.save(newUser);
        redirectAttributes.addFlashAttribute("message", "회원 가입 성공!");
        return "redirect:/login";
    }
}

