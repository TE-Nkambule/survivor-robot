package com.robot.survivormanagement.repository;

import com.robot.survivormanagement.entity.Flag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlagRepository extends JpaRepository<Flag, String> {

}
