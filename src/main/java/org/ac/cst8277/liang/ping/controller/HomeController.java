package org.ac.cst8277.liang.ping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String home() {
        // This is where you handle successful logins
        return "home" ;  // Make sure you have a "home.html" in your resources/templates directory
    }
}
