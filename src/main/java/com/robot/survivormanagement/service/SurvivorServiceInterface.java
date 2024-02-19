package com.robot.survivormanagement.service;

import com.robot.survivormanagement.entity.Inventory;
import com.robot.survivormanagement.entity.Location;
import com.robot.survivormanagement.entity.Survivor;

import java.util.List;

public interface SurvivorServiceInterface {

    Survivor createSurvivor(Survivor survivor);
    boolean createInventory(String survivorId, Inventory inventoryData);

    List<Survivor> listOfSurvivors();

    Survivor getSurvivorById(String id);

    Location updateLocationData(String survivorId, Location survivorLocationData);
}
