package com.website.violation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter@Setter@ToString
public class ViolationDto {
    private String deviceId;
    private String helmet;
    private String image;
}
