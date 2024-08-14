package org.ac.cst8277.liang.ping.service;

import org.ac.cst8277.liang.ping.entity.UserRole;
import org.ac.cst8277.liang.ping.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    public List<UserRole> getAllUserRoles() {
        return userRoleRepository.findAll();
    }

    public UserRole addUserRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }
}
