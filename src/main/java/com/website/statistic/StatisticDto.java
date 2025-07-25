package com.website.statistic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter@Setter
public class StatisticDto {
    private int todayDetectionCount;
    private long totalViolationCount;
    private String  complianceRate;
    private String activateCamera;

    public StatisticDto(int todayDetectionCount, long totalViolationCount, float complianceRate, String activateCamera) {
        this.todayDetectionCount = todayDetectionCount;
        this.totalViolationCount = totalViolationCount;
        this.complianceRate = complianceRate+"%";
        this.activateCamera = activateCamera;
    }
}
