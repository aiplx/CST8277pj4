package org.ac.cst8277.liang.ping.repository;

import org.ac.cst8277.liang.ping.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, String> {
}