package org.ac.cst8277.liang.ping.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionId;

    @ManyToOne
    @JoinColumn(name = "subscriber_id", nullable = false)
    private User subscriber;

    @ManyToOne
    @JoinColumn(name = "producer_id", nullable = false)
    private User producer;
}
