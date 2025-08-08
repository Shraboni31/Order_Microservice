package com.fullmicroservice.inventory_service.controller;

import com.fullmicroservice.inventory_service.dto.InventoryResponse;
import com.fullmicroservice.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<InventoryResponse> >isInStock(@RequestParam("skuCode") List<String> skuCodes){
        return ResponseEntity.ok(inventoryService.isInStock(skuCodes));
    }
}
