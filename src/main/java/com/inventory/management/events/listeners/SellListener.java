package com.inventory.management.events.listeners;
import com.inventory.management.events.Sell;
import org.springframework.context.ApplicationListener;

/**
 * Listener for the Sell event
 */
public class SellListener implements ApplicationListener<Sell> {
    /**
     * Method called when the user wants to sell a product.
     */
    @Override
    public void onApplicationEvent(Sell event) {
        System.out.println("Sell product " + event.getProduct().getName());
    }
}
