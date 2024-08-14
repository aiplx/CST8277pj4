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
    
    @Column(nullable = false)
    private String tokenType;

    @PrePersist
    protected void onCreate() {
        token = UUID.randomUUID().toString();
        expirationTime = LocalDateTime.now().plusMinutes(15); // Set the expiration time to 15 minutes from now
        tokenType = "Bearer";

    }

    public boolean isExpired() {
        return expirationTime.isBefore(LocalDateTime.now());
    }
}
