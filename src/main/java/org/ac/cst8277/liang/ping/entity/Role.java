package org.ac.cst8277.liang.ping.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;


@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(nullable = false, length = 50)
    private String roleName;

    @OneToMany(mappedBy = "role")
    private Set<UserRole> userRoles;
}
