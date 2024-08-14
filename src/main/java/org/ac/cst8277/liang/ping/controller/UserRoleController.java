package org.ac.cst8277.liang.ping.controller;

import org.ac.cst8277.liang.ping.entity.UserRole;
import org.ac.cst8277.liang.ping.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userroles")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping
    public List<UserRole> getAllUserRoles() {
        return userRoleService.getAllUserRoles();
    }

    @PostMapping
    public UserRole addUserRole(@RequestBody UserRole userRole) {
        return userRoleService.addUserRole(userRole);
    }
}