package com.robot.survivormanagement.repository;

import com.robot.survivormanagement.entity.Robot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RobotRepository extends JpaRepository<Robot, String> {
}
