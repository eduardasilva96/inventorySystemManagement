package com.inventory.management.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.inventory.management.dto.Warehouse;
import com.inventory.management.helper.JsonFileHelper;

import org.springframework.util.ResourceUtils;

public abstract class AbstractService {

    /**
     * An abstract service class to load the file specified (inventory/products)
     */
    protected Warehouse loadFile(String filename) throws IOException {
        File file = ResourceUtils.getFile(filename);
        String json = new String(Files.readAllBytes(file.toPath()));
        return JsonFileHelper.fromJsonToObject(json, Warehouse.class);

    }
}
