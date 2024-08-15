package org.ac.cst8277.liang.ping.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tokens")
public class Token {

    @Id
    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime expirationTime;

    @PrePersist
    protected void onCreate() {
        this.token = UUID.randomUUID().toString();
        this.expirationTime = LocalDateTime.now().plusMinutes(15);  // Token valid for 15 minutes
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expirationTime);
    }
}