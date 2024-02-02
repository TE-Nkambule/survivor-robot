package com.robot.survivormanagement.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_generator")
    @SequenceGenerator(name = "location_generator",
            sequenceName = "location_sequence",
            initialValue = 7, allocationSize = 1)
    @Column(name = "id")
    private String id;

    private Long latitude;
    private Long longitude;

    @ManyToOne
    private Survivor survivor;

    public void setId(String id) {
        String preId= "L";
        this.id = preId + id;
    }
}
