package com.robot.survivormanagement.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robot.survivormanagement.entity.Robot;
import com.robot.survivormanagement.repository.RobotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RobotService implements RobotServiceInterface {

    private static final String ROBOT_URL = "https://robotstakeover20210903110417.azurewebsites.net/robotcpu";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RobotRepository robotRepository;

    public boolean getRobotData() {

        try {
            ResponseEntity<Robot[]> responseEntity = restTemplate.getForEntity(ROBOT_URL, Robot[].class);
            Robot[] robotsArray = responseEntity.getBody();

            if (robotsArray != null) {
                List<Robot> robots = Arrays.asList(robotsArray);
                robotRepository.saveAll(robots);
                return true;
            } else {
                System.err.println("The data was not extracted successfully.");
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error occurred while fetching data from the URL: " + e.getMessage());
            return false;
        }
    }

    public List<Robot> getAllRobots() {

        return robotRepository.findAll();
    }
}
