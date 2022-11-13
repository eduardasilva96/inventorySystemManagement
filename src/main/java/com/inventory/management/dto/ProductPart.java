package com.inventory.management.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * An article of the product
 */
@Data
public class ProductPart implements Serializable {
    @JsonProperty("art_id")
    private int artId;
    @JsonProperty("amount_of")
    private int amountOf;
}
