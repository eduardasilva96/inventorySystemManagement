package com.inventory.management.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * The article object
 */
@Data
public class Article implements Serializable {
    @JsonProperty("art_id")
    private int artId;
    private String name;
    private int stock;
}
