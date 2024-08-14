package org.ac.cst8277.liang.ping.service;

import org.ac.cst8277.liang.ping.entity.Role;
import org.ac.cst8277.liang.ping.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role addRole(Role role) {
        return roleRepository.save(role);
    }
}