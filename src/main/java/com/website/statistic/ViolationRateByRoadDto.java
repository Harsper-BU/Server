package com.website.statistic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class ViolationRateByRoadDto {
    private String road;
    private Long violationCount;
    private Long totalCount;
    private Double violationRate;
}
