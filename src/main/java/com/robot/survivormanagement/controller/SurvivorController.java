package com.robot.survivormanagement.controller;

import com.robot.survivormanagement.entity.Inventory;
import com.robot.survivormanagement.entity.Location;
import com.robot.survivormanagement.entity.Survivor;
import com.robot.survivormanagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.List;

@RestController
@RequestMapping("/survivors")
public class SurvivorController {
    @Autowired
    private SurvivorServiceInterface survivorServiceInterface;
    @Autowired
    private FlagServiceInterface flagServiceInterface;

    //Creating a survivor
    @PostMapping
    public ResponseEntity<Survivor> postASurvivor(@RequestBody Survivor survivor) {

        try {
            Survivor screatedSurvivor = survivorServiceInterface.createSurvivor(survivor);
            return ResponseEntity.status(HttpStatus.CREATED).body(screatedSurvivor);
        } catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @PutMapping
    public ResponseEntity<Inventory> updateInventory(@RequestParam String survivorId, @RequestBody Inventory inventory){
        boolean successfullyUpdated = survivorServiceInterface.createInventory(survivorId, inventory);

        if (successfullyUpdated){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //Getting a list of all survivors
    @GetMapping("/list")
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
            Location savedLocation = survivorServiceInterface.updateLocationData(survivorId, location);
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
    public ResponseEntity<String> percentageOfInfected() {
        List<Survivor> survivors = survivorServiceInterface.listOfSurvivors();

        if (!survivors.isEmpty()) {
            int noOfInfected = flagServiceInterface.getListOfInfected();

            if (noOfInfected > 0) {
                int noOfSurvivors = (int) survivorServiceInterface.listOfSurvivors().size();

                float infectedPercentage = ((float) noOfInfected / noOfSurvivors) * 100;

                DecimalFormat df = new DecimalFormat("#.##");
                String roundedPercentage = df.format(infectedPercentage);

                return ResponseEntity.ok(roundedPercentage);
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
    public ResponseEntity<String> percentageOfNonInfected() {
        List<Survivor> survivors = survivorServiceInterface.listOfSurvivors();

        if (!survivors.isEmpty()) {
            int noOfNonInfected = flagServiceInterface.getListOfNonInfected();

            if (noOfNonInfected > 0) {
                int noOfSurvivors = (int) survivorServiceInterface.listOfSurvivors().size();

                float nonInfectedPercentage = ((float) noOfNonInfected / noOfSurvivors) * 100;

                DecimalFormat df = new DecimalFormat("#.##");
                String roundedPercentage = df.format(nonInfectedPercentage);

                return ResponseEntity.ok(roundedPercentage);
            } else {
                System.err.println("Oh no, all survivors are infected...");
                return ResponseEntity.noContent().build();
            }
        }else {
            return ResponseEntity.noContent().build();
        }
    }



}

