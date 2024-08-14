package org.ac.cst8277.liang.ping.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Setter
    @Getter
    @Column(nullable = false, length = 50)
    private String userName;

    @Setter
    @Getter
    @Column(nullable = false, length = 50)
    private String userPwd;

    @OneToMany(mappedBy = "user")
    private Set<UserRole> userRoles;

    @OneToMany(mappedBy = "user")
    private Set<Message> messages;

    @OneToMany(mappedBy = "subscriber")
    private Set<Subscription> subscriptions;

    @OneToMany(mappedBy = "producer")
    private Set<Subscription> producedSubscriptions;
}
