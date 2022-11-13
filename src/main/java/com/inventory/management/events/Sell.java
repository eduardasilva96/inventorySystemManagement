package com.inventory.management.events;

import com.inventory.management.dto.Product;

import org.springframework.context.ApplicationEvent;

import lombok.Data;

@Data
public class Sell extends ApplicationEvent {

    /* product to sell */
    private Product product;

    /**
     * Event to trigger when a sell occurs
     */
    public Sell(Object source, Product product) {
        super(source);
        this.product = product;
    }
}