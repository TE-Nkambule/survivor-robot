package com.robot.survivormanagement.entity;

import com.robot.survivormanagement.Gender;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Survivor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "survivor_generator")
    @SequenceGenerator(name = "survivor_generator",
                        sequenceName = "survivor_sequence",
                        initialValue = 11, allocationSize = 1)
    @Column(name = "id")
    private String id;

    private String name;

    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToOne
    @JoinColumn(name = "flag_id")
    private Flag flag;

    @OneToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    public void setId(String id) {
        String preId= "surv_";
        this.id = preId + id;
    }
}
