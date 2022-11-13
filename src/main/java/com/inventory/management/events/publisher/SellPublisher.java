package com.inventory.management.events.publisher;

import com.inventory.management.dto.Product;
import com.inventory.management.events.Sell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Publisher for the sell product event
 */
@Component
public class SellPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishCustomEvent(final Product product) {
        Sell customSpringEvent = new Sell(this, product);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}
