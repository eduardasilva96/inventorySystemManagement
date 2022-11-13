package com.inventory.management.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * The warehouse object/inventory
 */
@Data
public class Warehouse {
    // all the products in the warehouse
    List<Product> products;
    // all the articles in the warehouse
    @JsonProperty("inventory")
    List<Article> articles;
}
