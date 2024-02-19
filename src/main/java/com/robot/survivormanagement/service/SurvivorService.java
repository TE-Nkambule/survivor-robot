package com.robot.survivormanagement.service;

import com.robot.survivormanagement.entity.Inventory;
import com.robot.survivormanagement.entity.Location;
import com.robot.survivormanagement.entity.Survivor;
import com.robot.survivormanagement.repository.InventoryRepository;
import com.robot.survivormanagement.repository.LocationRepository;
import com.robot.survivormanagement.repository.SurvivorRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurvivorService implements SurvivorServiceInterface {

    @Autowired
    private SurvivorRespository survivorRespository;

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private LocationRepository locationRepository;

    public Survivor createSurvivor(Survivor survivor){

        return survivorRespository.save(survivor);
    }

    public boolean createInventory(String survivorId, Inventory inventoryData){

        Survivor getSurvivor = survivorRespository.findById(String.valueOf(survivorId)).orElse(null);

        if (getSurvivor != null){
            Inventory inventory = getSurvivor.getInventory();

            inventory.setAmmunition(inventoryData.isAmmunition());
            inventory.setFood(inventoryData.isFood());
            inventory.setMedication(inventoryData.isMedication());
            inventory.setWater(inventoryData.isWater());
            inventoryRepository.save(inventory);

            return true;

        }else {
            System.err.println("The survivor ID does not exist");
            return false;
        }

    }

    public List<Survivor> listOfSurvivors(){

        List<Survivor> survivors = survivorRespository.findAll();
        return survivors;
    }

    public Survivor getSurvivorById(String survivorId){

        Survivor survivor = survivorRespository.findById(survivorId).orElse(null);
        return survivor;
    }

    public Location updateLocationData(String survivorId, Location survivorLocationData){

        Survivor existingSurvivor = getSurvivorById(survivorId);

        if(existingSurvivor != null){
            Location location = existingSurvivor.getLocation();

            location.setLongitude(survivorLocationData.getLongitude());
            location.setLatitude(survivorLocationData.getLatitude());
            locationRepository.save(location);

            return location;
        }else {
            System.err.println("The Survivor ID entered does not exist!");
            return null;
        }
    }



}
