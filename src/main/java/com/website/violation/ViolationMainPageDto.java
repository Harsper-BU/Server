package com.website.violation;

import com.website.entity.Violation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class ViolationMainPageDto {
    private Long id;
    private String imageLocation;
    private String helmetStatus;
    private LocalDateTime timestamp;

    private String deviceId;
    private String address;

    public ViolationMainPageDto(Violation violation) {
        this.id = violation.getId();
        this.imageLocation = violation.getImageLocation();
        this.helmetStatus = violation.getHelmetStatus();
        this.timestamp = violation.getTimestamp();
        this.deviceId = violation.getCamera().getDeviceId();
        this.address = violation.getCamera().getAddress();
    }
}
