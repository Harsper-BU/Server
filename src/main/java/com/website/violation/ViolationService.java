package com.website.violation;

import com.website.entity.Camera;
import com.website.entity.Violation;
import com.website.repository.CameraRepository;
import com.website.repository.ViolationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ViolationService {
    private final ViolationRepository violationRepository;
    private final CameraRepository cameraRepository;


    public ViolationService(ViolationRepository violationRepository, CameraRepository cameraRepository) {
        this.violationRepository = violationRepository;
        this.cameraRepository = cameraRepository;
    }

    public Page<ViolationMainPageDto> getViolationDataByPage(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "timestamp"));
        Page<Violation> violations = violationRepository.findAll(pageable);
        return violations.map(ViolationMainPageDto::new);

    }
    @Transactional
    public void insertViolationData(ViolationDto violationDto) {
        Camera camera = cameraRepository.getReferenceById(violationDto.getDeviceId());
        Violation violation = new Violation(
                LocalDateTime.now(),
                violationDto.getHelmetStatus(),
                "이미지 경로",
                camera
        );
        violationRepository.save(violation);
    }

}
