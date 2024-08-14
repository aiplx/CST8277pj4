package org.ac.cst8277.liang.ping.controller;

import org.ac.cst8277.liang.ping.entity.User;
import org.ac.cst8277.liang.ping.entity.Token;
import org.ac.cst8277.liang.ping.exception.UnauthorizedException;
import org.ac.cst8277.liang.ping.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserManagementService userManagementService;

    @GetMapping
    public List<User> getAllUsers(@RequestParam(required = false) String token) {
        if (token == null || !userManagementService.validateToken(token)) {
            throw new UnauthorizedException();
        }
        return userManagementService.getAllUsers();
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userManagementService.addUser(user);
    }

    @PostMapping("/authenticate")
    public Token authenticate(@RequestParam String userName, @RequestParam String userPwd) {
        return userManagementService.authenticate(userName, userPwd);
    }

    @GetMapping("/validate")
    public boolean validateToken(@RequestParam String token) {
        return userManagementService.validateToken(token);
    }
}
