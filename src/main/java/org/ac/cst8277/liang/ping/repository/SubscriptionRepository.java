package org.ac.cst8277.liang.ping.repository;

import org.ac.cst8277.liang.ping.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findBySubscriberUserId(Long subscriberId);
}
