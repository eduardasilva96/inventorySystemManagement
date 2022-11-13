package com.inventory.management.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.inventory.management.dto.Article;
import com.inventory.management.dto.ProductPart;
import com.inventory.management.dto.Warehouse;
import com.inventory.management.events.Sell;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryService extends AbstractService {

    @Value("${warehouse.inventory.file}")
    private String inventoriesFile;

    private Map<Integer, Article> inventories = new HashMap<>();

    /**
     * initalize the service and load contents from the provided file
     */
    @PostConstruct
    public void init() throws IOException {
        File file;
            System.out.println("initializing inventory service");
            Warehouse warehouse = loadFile(inventoriesFile);
            if (warehouse != null && warehouse.getArticles() != null) {
                System.out.println("loaded " + warehouse.getArticles().size() + " items");
                addArticles(warehouse.getArticles());
            } else {
                System.out.println("Nothing was loaded");
            }
    }

    /**
     * Handle sell event by updating the stock
     */
    @EventListener
    public void handleContextStart(Sell event) {
        System.out.println("Selling " + event.getProduct().getName());
        List<ProductPart> parts = event.getProduct().getProductParts();
        for (ProductPart productPart : parts) {
            if (isArticleAvailable(productPart.getArtId(), productPart.getAmountOf())) {
                removeArticleFromStock(productPart.getArtId(), productPart.getAmountOf());
            } else {
                // do nothing (?)
            }
        }

    }

    /**
     * remove the article amounts form stock of a certain product that was sold
     */
    private void removeArticleFromStock(int artId, int amount) {
        System.out.println("Removing " + amount + " articles from stock with id "+ artId);
        Article article = getArticle(artId);
        article.setStock(article.getStock() - amount);
    }

    /**
     * Getter for the articles
     */
    private Article getArticle(int artId) {
        return this.inventories.get(artId);
    }

    /**
     * Add an article to inventory
     */
    public void addArticles(List<Article> articles) {
        this.inventories = articles.stream().collect(Collectors.toMap(Article::getArtId, article -> article));
    }

    /**
     * Check for availability of a given amount of articles.
     * if the amount is less than the quantity in stock AND the quantity in stock is higher than zero -> article is available
     */
    public boolean isArticleAvailable(int artId, int amount) {
        if (this.inventories.containsKey(artId)) {
            Article article = this.inventories.get(artId);
            return amount <= article.getStock() && article.getStock() >= 0;
        } else {
            return false;
        }

    }

}
