package com.inventory.management.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * The product object
 */
@Data
public class Product implements Serializable {

    private String name;

    //Since each product holds articles, this is a property of a product
    @JsonProperty("contain_articles")
    private List<ProductPart> productParts;
}
