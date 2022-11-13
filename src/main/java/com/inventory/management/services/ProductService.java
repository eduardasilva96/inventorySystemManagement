package com.inventory.management.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.inventory.management.dto.Product;
import com.inventory.management.dto.ProductPart;
import com.inventory.management.dto.Warehouse;
import com.inventory.management.events.publisher.SellPublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends AbstractService {

    @Value("${warehouse.products.file}")
    private String productsFile;

    private Map<String, Product> products = new HashMap<>();

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private SellPublisher sellProductPublisher;

    /**
     * initalize the service and load contents from the provided file
     */
    @PostConstruct
    public void init() throws IOException {
            System.out.println("initializing products service");
            Warehouse warehouse = loadFile(productsFile);
            if (warehouse != null && warehouse.getProducts() != null) {
                System.out.println("loaded " + warehouse.getProducts().size() + " products");
                addProducts(warehouse.getProducts());
            } else {
                System.out.println("Nothing was loaded");
            }

    }

    /**
     * Getter for products
     */
    public Product getProduct(String name) {
        return this.products.get(name);
    }

    /**
     * List only available products
     * 
     */
    public Map<String, Product> listAvailableProducts() {
        Map<String, Product> availProducts = new HashMap<>();
        this.products.forEach((k, product) -> {
            if (isProductAvailable(product)) {
                availProducts.put(product.getName(), product);
            }
        });

        return availProducts;

    }

    /**
     * Check is the product is available to sell
     */
    public boolean isProductAvailable(Product product) {
        for (ProductPart part : product.getProductParts()) {
            if (!inventoryService.isArticleAvailable(part.getArtId(), part.getAmountOf())) {
                return false;
            }
        }

        return true;
    }

    /**
     * method to add products
     */
    public void addProducts(List<Product> products) {
        this.products = products.stream().collect(Collectors.toMap(Product::getName, product -> product));
    }

    /**
     * method to sell the product if it is still in stock.
     */
    public void sellProduct(String name) {
        Product product = getProduct(name);
        if (isProductAvailable(product)) {
            // actually sell the product
            sellProductPublisher.publishCustomEvent(product);
        } else {
            System.out.println("No longer in stock");

        }
    }

}
