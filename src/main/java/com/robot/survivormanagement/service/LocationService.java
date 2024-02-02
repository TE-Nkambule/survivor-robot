package com.robot.survivormanagement.service;

import com.robot.survivormanagement.entity.Location;
import com.robot.survivormanagement.entity.Survivor;
import com.robot.survivormanagement.repository.SurvivorRespository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@Transactional
public class LocationService implements LocationServiceInterface {

    @Autowired
    private SurvivorRespository survivorRespository;

    public Survivor getSurvivorById(String survivorId){
        Optional<Survivor> optionalSurvivor = survivorRespository.findById(survivorId);
        return optionalSurvivor.orElse(null);
    }
    public Location updateLocationData(String survivorId, Location survivorLocationData){

        Survivor existingSurvivor = getSurvivorById(survivorId);

        if(existingSurvivor != null){
            Location location = existingSurvivor.getLocation();

            location.setLongitude(survivorLocationData.getLongitude());
            location.setLatitude(survivorLocationData.getLatitude());

            return location;
        }else {
            System.err.println("The Survivor ID entered does not exist!");
            return null;
        }
    }
}
