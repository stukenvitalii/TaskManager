package com.example.TaskManager.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CookieController {
    @GetMapping("/set-cookie")
    public String setCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("myCookie", "cookieValue");

        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        response.addCookie(cookie);

        return "cookieSet";
    }
}
