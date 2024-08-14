package org.ac.cst8277.liang.ping.controller;

import org.ac.cst8277.liang.ping.entity.Role;
import org.ac.cst8277.liang.ping.exception.UnauthorizedException;
import org.ac.cst8277.liang.ping.service.RoleService;
import org.ac.cst8277.liang.ping.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserManagementService userManagementService;

    @GetMapping
    public List<Role> getAllRoles(@RequestParam String token) {
        if (userManagementService.validateToken(token)) {
            return roleService.getAllRoles();
        }
        throw new UnauthorizedException();
    }

    @PostMapping
    public Role addRole(@RequestBody Role role, @RequestParam String token) {
        if (userManagementService.validateToken(token)) {
            return roleService.addRole(role);
        }
        throw new UnauthorizedException();
    }
}