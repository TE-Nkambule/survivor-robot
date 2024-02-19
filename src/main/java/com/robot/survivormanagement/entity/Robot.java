package com.robot.survivormanagement.entity;

import com.robot.survivormanagement.Category_option;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
public class Robot {
    @Id
    private String model;

    private String serial_number;
    private Timestamp manufactured_date;

    @Enumerated(EnumType.STRING)
    private Category_option category_option;


}
