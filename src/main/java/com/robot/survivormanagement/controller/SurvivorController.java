package com.robot.survivormanagement.controller;

import com.robot.survivormanagement.entity.Inventory;
import com.robot.survivormanagement.entity.Location;
import com.robot.survivormanagement.entity.Survivor;
import com.robot.survivormanagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/survivors")
public class SurvivorController {
    @Autowired
    private SurvivorServiceInterface survivorServiceInterface;
    @Autowired
    private LocationServiceInterface locationServiceInterface;
    @Autowired
    private FlagServiceInterface flagServiceInterface;
    @Autowired
    private InventoryServiceInterface inventoryServiceInterface;

    //Creating a survivor
    @PostMapping
    public ResponseEntity<Survivor> postASurvivor(@RequestBody Survivor survivor) {

        try {
            Survivor screatedSurvivor = survivorServiceInterface.createATeam(survivor);
            return ResponseEntity.status(HttpStatus.CREATED).body(screatedSurvivor);
        } catch (Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    //"/survivor/inventory"
    @PostMapping("/inventory")
    public ResponseEntity<Inventory> postInventory(@RequestBody Inventory inventory) {

        try {
            Inventory createInventory = inventoryServiceInterface.createATeam(inventory);
            return ResponseEntity.status(HttpStatus.CREATED).body(createInventory);
        } catch (Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    //Getting a list of all survivors
    @GetMapping
    public ResponseEntity<List<Survivor>> getAllSurvivors() {
        List<Survivor> survivors = survivorServiceInterface.listOfSurvivors();

        if(survivors.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(survivors);
        }

    }

    //Updating the last location
    @PutMapping("/{survivorId}")
    public ResponseEntity<Location> updateSurvivorLocation(@PathVariable String survivorId, @RequestBody Location location) {
        Survivor existingSurvivor = survivorServiceInterface.getSurvivorById(survivorId);

        if (existingSurvivor != null) {
            Location savedLocation = locationServiceInterface.updateLocationData(survivorId, location);
            return ResponseEntity.ok(savedLocation);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PutMapping("/report")
    public ResponseEntity<String> reportInfectedSurvivor(@RequestParam String reporterId,@RequestParam String reportedId){
        boolean successfullyReported = flagServiceInterface.report(reporterId, reportedId);

        if(successfullyReported){
            return ResponseEntity.ok("Successfully reported the survivor.");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid reporter's survivor ID or invalid reported's survivor ID.");
        }
    }

    //Number of infected Survivors
    @GetMapping("/infected")
    public ResponseEntity<Integer> ListOfInfectedSurvivors(){
        int infectedSurvivors = flagServiceInterface.getListOfInfected();

        return ResponseEntity.ok(infectedSurvivors);
    }

    //Number of non-infected Survivors
    @GetMapping("/non-infected")
    public ResponseEntity<Integer> ListOfNonInfected(){
        int nonInfectedSurvivors = flagServiceInterface.getListOfNonInfected();

        return ResponseEntity.ok(nonInfectedSurvivors);
    }

    //Get percentage of infected
    @GetMapping("/infected/percentage")
    public ResponseEntity<Float> percentageOfInfected() {
        List<Survivor> survivors = survivorServiceInterface.listOfSurvivors();

        if (!survivors.isEmpty()) {
            int noOfInfected = flagServiceInterface.getListOfInfected();

            if (noOfInfected > 0) {
                int noOfSurvivors = (int) survivorServiceInterface.listOfSurvivors().size();

                float infectedPercentage = ((float) noOfInfected / noOfSurvivors) * 100;
                return ResponseEntity.ok(infectedPercentage);
            } else {
                System.err.println("Yay! There are no infected survivors!!");
                return ResponseEntity.noContent().build();
            }
        }else {
               return ResponseEntity.noContent().build();
        }

    }



    //Get percentage of non-infected
    @GetMapping("/non-infected/percentage")
    public ResponseEntity<Float> percentageOfNonInfected() {
        List<Survivor> survivors = survivorServiceInterface.listOfSurvivors();

        if (!survivors.isEmpty()) {
            int noOfNonInfected = flagServiceInterface.getListOfNonInfected();

            if (noOfNonInfected > 0) {
                int noOfSurvivors = (int) survivorServiceInterface.listOfSurvivors().size();

                float nonInfectedPercentage = ((float) noOfNonInfected / noOfSurvivors) * 100;
                return ResponseEntity.ok(nonInfectedPercentage);
            } else {
                System.err.println("Oh no, all survivors are infected...");
                return ResponseEntity.noContent().build();
            }
        }else {
            return ResponseEntity.noContent().build();
        }
    }



}

