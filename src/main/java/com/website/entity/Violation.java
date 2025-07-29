package com.website.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "violation")
@AllArgsConstructor@NoArgsConstructor
@Getter@Setter
public class Violation {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDateTime timestamp;
    @Column(name = "helmet_status", length = 45)
    private String helmetStatus;
    @Column(name = "image_location")
    private String imageLocation;
    @Column(name = "image_name")
    private String imageName;

    @JoinColumn(name = "device_id")
    @ManyToOne
    private Camera camera;


    public Violation(LocalDateTime timestamp, String helmetStatus, String imageLocation, String imageName, Camera camera) {
        this.timestamp = timestamp;
        this.helmetStatus = helmetStatus;
        this.imageLocation = imageLocation;
        this.imageName = imageName;
        this.camera = camera;
    }

}
