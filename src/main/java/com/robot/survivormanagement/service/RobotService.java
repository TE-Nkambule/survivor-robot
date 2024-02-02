package com.robot.survivormanagement.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robot.survivormanagement.entity.Robot;
import com.robot.survivormanagement.repository.RobotRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;


@Service
public class RobotService implements RobotServiceInterface {
    @Autowired
    private RobotRepository robotRepository;

    private static final String ROBOT_URL = "https://robotstakeover20210903110417.azurewebsites.net/robotcpu";

    ObjectMapper objectMapper = new ObjectMapper();
    public RobotService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    //get the information from the link the store it to the database
    public boolean getRobotData() throws IOException {

        JsonNode jsonNode = objectMapper.readTree(new URL(ROBOT_URL));

        Robot[] robotsArray = objectMapper.treeToValue(jsonNode, Robot[].class);

        if (robotsArray != null) {
            List<Robot> robots = Arrays.asList(robotsArray);
            robotRepository.saveAll(robots);
            return true;
        }else {

            System.err.println("The data was not extracted successfully.");
            return false;
        }
    }

}
