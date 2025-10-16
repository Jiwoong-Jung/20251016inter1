package com.du.inter1.controller;

import com.du.inter1.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        // 로그인 여부 확인
        User currentUser = (User) request.getSession().getAttribute("user");

        // 로그인 상태라면 게시판 목록으로 리다이렉트
        if (currentUser != null) {
            return "redirect:/posts"; // 게시판 목록
        } else {
            return "redirect:/login"; // 로그인 페이지
        }
    }
}
