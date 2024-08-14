package org.ac.cst8277.liang.ping.repository;

import org.ac.cst8277.liang.ping.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
