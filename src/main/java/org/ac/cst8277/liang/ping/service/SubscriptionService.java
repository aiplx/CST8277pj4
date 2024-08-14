package org.ac.cst8277.liang.ping.service;

import org.ac.cst8277.liang.ping.entity.Subscription;
import org.ac.cst8277.liang.ping.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public List<Subscription> getSubscriptionsBySubscriber(Long subscriberId) {
        return subscriptionRepository.findBySubscriberUserId(subscriberId);
    }

    public Subscription addSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }
}
