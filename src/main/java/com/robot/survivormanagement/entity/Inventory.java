package com.robot.survivormanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_generator")
    @SequenceGenerator(name = "inventory_generator",
            sequenceName = "inventory_sequence",
            initialValue = 11, allocationSize = 1)
    @Column(name = "id")
    private String id;


    private boolean water;

    private boolean food;


    private boolean medication;

    private boolean ammunition;

    @OneToOne(mappedBy = "inventory")
    private Survivor survivor;

    public void setId(String id) {
        String preId= "I";
        this.id = preId + id;
    }
}
