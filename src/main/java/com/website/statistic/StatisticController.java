package com.website.statistic;

import com.website.violation.HourlyViolationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth/statistics")
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }
    @GetMapping
    public ResponseEntity<StatisticDto> getStatistic(){
        return ResponseEntity.ok().body(statisticService.getStatistic());
    }
    @GetMapping("/violation/rate")
    public ResponseEntity<List<ViolationRateByRoadDto>> getViolationRateGroupedByRoad(){
        return ResponseEntity.ok().body(statisticService.getViolationRateGroupedByRoad());
    }
    @GetMapping("/violation/hourly")
    public ResponseEntity<List<HourlyViolationDto>> getHourlyViolationStats(){
        return ResponseEntity.ok().body(statisticService.getHourlyViolationStats());
    }
}
