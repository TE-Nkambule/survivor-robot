package com.robot.survivormanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Flag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flag_generator")
    @SequenceGenerator(name = "flag_generator",
            sequenceName = "flag_sequence",
            initialValue = 11, allocationSize = 1)
    @Column(name = "id")
    private String id;

    @Column(name = "noOfReports")
    private int noOfReports;

    private boolean status;

    @OneToOne(mappedBy = "flag")
    private Survivor survivor;

    public void setId(String id) {
        String preId= "F";
        this.id = preId + id;
    }
}
