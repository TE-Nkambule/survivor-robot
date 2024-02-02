package com.robot.survivormanagement.controller;


import com.robot.survivormanagement.entity.Robot;
import com.robot.survivormanagement.service.RobotServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;

@RestController
@RequestMapping("/robots")
public class RobotController {
    @Autowired
    private RobotServiceInterface robotServiceInterface;

    @PostMapping
    public ResponseEntity<Robot> addRobotsToDatabase() throws IOException {
        boolean successfullyAdded = robotServiceInterface.getRobotData();

        if (successfullyAdded){

            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.noContent().build();
        }
    }
}
