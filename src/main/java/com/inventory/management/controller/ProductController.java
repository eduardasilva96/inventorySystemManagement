package com.inventory.management.controller;

import java.util.Map;

import com.inventory.management.dto.Product;
import com.inventory.management.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * list all products
     */
    @GetMapping(value = "/rest/product", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String, Product>> listProducts() {
        Map<String, Product> products = productService.listAvailableProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Method to sell a product
     */
    @PutMapping(value = "/rest/product/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Void> sellProduct(@PathVariable String name) {
        System.out.println("Sell " + name);

        productService.sellProduct(name);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
