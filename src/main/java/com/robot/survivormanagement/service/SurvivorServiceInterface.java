package com.robot.survivormanagement.service;

import com.robot.survivormanagement.entity.Survivor;

import java.util.List;

public interface SurvivorServiceInterface {
    Survivor createATeam(Survivor survivor);

    List<Survivor> listOfSurvivors();

    Survivor getSurvivorById(String id);
}
