package com.fullmicroservice.inventory_service.service;

import com.fullmicroservice.inventory_service.dto.InventoryResponse;
import com.fullmicroservice.inventory_service.repository.InventoryRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)//////????????????????????????????
    @SneakyThrows // don't use this annotation in prod it is to handle exception of sleep()
    public List<InventoryResponse> isInStock(List<String> skuCodes){

//        log.info("Wait started checking time out fallback scenario");
//        Thread.sleep(10000);
//        log.info("Wait ended checking time out fallback scenario");
         List<InventoryResponse> responses= inventoryRepository.findBySkuCodeIn(skuCodes).stream()
                 .map(inventory ->
                     InventoryResponse.builder()
                             .skuCode(inventory.getSkuCode())
                             .isInStock(inventory.getQuantity() > 0)
                             .build()
                 )
                 .toList();

         return responses;

    }
}
