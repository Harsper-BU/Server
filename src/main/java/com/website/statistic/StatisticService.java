package com.website.statistic;

import com.website.repository.CameraRepository;
import com.website.repository.ViolationRepository;
import com.website.violation.HourlyViolationDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatisticService {
    private final ViolationRepository violationRepository;
    private final CameraRepository cameraRepository;
    public StatisticService(ViolationRepository violationRepository, CameraRepository cameraRepository) {
        this.violationRepository = violationRepository;
        this.cameraRepository = cameraRepository;
    }
    public StatisticDto getStatistic() {
        int todayDetectionCount = violationRepository.countByTimestamp(LocalDateTime.now());
        long totalDetectionCount = violationRepository.count();
        int totalViolationCount = violationRepository.countByHelmetStatus("violation");
        float complianceRate = 0;
        if (totalDetectionCount > 0) {
            complianceRate = ((float) (totalDetectionCount - totalViolationCount) / totalDetectionCount) * 100;
        }
        complianceRate = Math.round(complianceRate * 10) / 10.0f;
        int activateCameraCount = cameraRepository.countByStatus("on");

        long totalCamera = cameraRepository.count();


        return new StatisticDto(
                todayDetectionCount,
                totalViolationCount,
                complianceRate,
                activateCameraCount + "/" + totalCamera
        );
    }
    public List<ViolationRateByRoadDto> getViolationRateGroupedByRoad() {
        List<Object[]> rows = violationRepository.getViolationStatsRaw();
        return rows.stream()
                .map(row -> new ViolationRateByRoadDto(
                        (String) row[0],
                        ((Number) row[1]).longValue(),  // BigInteger → Number
                        ((Number) row[2]).longValue(),
                        ((Number) row[3]).doubleValue() // BigDecimal → Number
                ))
                .toList();
    }


    public List<HourlyViolationDto> getHourlyViolationStats() {
        return violationRepository.getHourlyViolations().stream()
                .map(row -> new HourlyViolationDto(
                        ((Integer) row[0]),
                        ((Number) row[1]).longValue()))
                .toList();
    }
}
