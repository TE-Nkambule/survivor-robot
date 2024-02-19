package com.robot.survivormanagement.controller;


import com.robot.survivormanagement.entity.Robot;
import com.robot.survivormanagement.service.RobotServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/robots")
public class RobotController {
    @Autowired
    private RobotServiceInterface robotServiceInterface;

    @PostMapping
    public ResponseEntity<Robot> addRobotsToDatabase(){
        boolean successfullyAdded = robotServiceInterface.getRobotData();

        if (successfullyAdded){

            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Robot>> getRobots(){

        List<Robot> robots= robotServiceInterface.getAllRobots();

        if(robots.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(robots);
        }
    }
}
