package com.robot.survivormanagement.service;

import com.robot.survivormanagement.entity.Robot;

import java.io.IOException;
import java.util.List;

public interface RobotServiceInterface {
    boolean getRobotData();
    List<Robot> getAllRobots();
}
