package com.robot.survivormanagement.repository;

import com.robot.survivormanagement.entity.Survivor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurvivorRespository extends JpaRepository<Survivor, String> {

}
