package com.website.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "camera")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Camera {
    @Id
    @Column(name = "device_id", length = 10)
    private String deviceId;
    @Column(length = 45)
    private String status;
    @Column
    private String address;
    @Column
    private LocalDateTime lastUpdate;
    @Column(name = "ip_address")
    private String ipAddress;
}
