package com.robot.survivormanagement.service;

import com.robot.survivormanagement.entity.Inventory;
import com.robot.survivormanagement.entity.Survivor;
import com.robot.survivormanagement.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService implements InventoryServiceInterface{
    @Autowired
    private InventoryRepository inventoryRepository;

    public Inventory createATeam(Inventory inventory){

        return inventoryRepository.save(inventory);
    }
}
