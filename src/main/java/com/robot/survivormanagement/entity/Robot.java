package com.robot.survivormanagement.entity;

import com.robot.survivormanagement.CategoryOption;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
public class Robot {
    @Id
    private String model;

    private String serialNumber;
    private Timestamp manufacturedDate;
    @Enumerated(EnumType.STRING)
    private CategoryOption categoryOption;


}
