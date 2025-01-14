package org.ac.cst8277.liang.ping.repository;

import org.ac.cst8277.liang.ping.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByUserUserId(Long userId);
}
