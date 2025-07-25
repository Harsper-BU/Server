package com.website.repository;

import com.website.entity.Violation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ViolationRepository extends JpaRepository<Violation, Long> {


    int countByHelmetStatus(String violation);
    @Query(
            value = """
SELECT
    CONCAT(SUBSTRING_INDEX(c.address, '로', 1), '로') AS road,
    COUNT(CASE WHEN v.helmet_status = 'violation' THEN 1 END) AS violation_count,
    COUNT(*) AS total_count,
    ROUND(COUNT(CASE WHEN v.helmet_status = 'violation' THEN 1 END) / COUNT(*) * 100, 2) AS violation_rate
FROM
    violation v
JOIN
    camera c ON v.device_id = c.device_id
GROUP BY
    road
ORDER BY
    violation_rate DESC;
    """,
            nativeQuery = true
    )
    List<Object[]> getViolationStatsRaw();
    @Query(value = """
    SELECT 
      HOUR(v.timestamp) AS hour,
      COUNT(*) AS count
    FROM violation v
    WHERE v.helmet_status = 'violation'
      AND v.timestamp >= NOW() - INTERVAL 1 DAY
    GROUP BY hour
    ORDER BY hour
""", nativeQuery = true)
    List<Object[]> getHourlyViolations();

    int countByTimestamp(LocalDateTime now);
}
