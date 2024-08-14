package org.ac.cst8277.liang.ping.controller;

import org.ac.cst8277.liang.ping.entity.Subscription;
import org.ac.cst8277.liang.ping.exception.UnauthorizedException;
import org.ac.cst8277.liang.ping.service.SubscriptionService;
import org.ac.cst8277.liang.ping.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private UserManagementService userManagementService;

    @GetMapping
    public List<Subscription> getAllSubscriptions(@RequestParam String token) {
        if (userManagementService.validateToken(token)) {
            return subscriptionService.getAllSubscriptions();
        }
        throw new UnauthorizedException();
    }

    @GetMapping("/subscriber/{subscriberId}")
    public List<Subscription> getSubscriptionsBySubscriber(@PathVariable Long subscriberId, @RequestParam String token) {
        if (userManagementService.validateToken(token)) {
            return subscriptionService.getSubscriptionsBySubscriber(subscriberId);
        }
        throw new UnauthorizedException();
    }

    @PostMapping
    public Subscription addSubscription(@RequestBody Subscription subscription, @RequestParam String token) {
        if (userManagementService.validateToken(token)) {
            return subscriptionService.addSubscription(subscription);
        }
        throw new UnauthorizedException();
    }
}
