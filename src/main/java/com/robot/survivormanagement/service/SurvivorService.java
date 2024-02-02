package com.robot.survivormanagement.service;

import com.robot.survivormanagement.entity.Survivor;
import com.robot.survivormanagement.repository.SurvivorRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurvivorService implements SurvivorServiceInterface {

    @Autowired
    private SurvivorRespository survivorRespository;


    public Survivor createATeam(Survivor survivor){

        return survivorRespository.save(survivor);
    }

    public List<Survivor> listOfSurvivors(){
        return (List<Survivor>) survivorRespository.findAll();
    }

    public Survivor getSurvivorById(String survivorId){
        Optional<Survivor> optionalSurvivor = survivorRespository.findById(survivorId);
        return optionalSurvivor.orElse(null);
    }



}
