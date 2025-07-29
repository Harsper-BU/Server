package com.website.violation;

import com.website.entity.Camera;
import com.website.entity.Violation;
import com.website.repository.CameraRepository;
import com.website.repository.ViolationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class ViolationService {
    private final ViolationRepository violationRepository;
    private final CameraRepository cameraRepository;
    @Value("${file.upload-dir}")
    private String dir;
    @Value("${file.download-url}")
    private String url;


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
        String imageName = UUID.randomUUID().toString();
        String path = dir+"\\"+imageName +".jpg";
        saveBase64ToFile(violationDto.getImage(), path);
        Violation violation = new Violation(
                LocalDateTime.now(),
                violationDto.getHelmet(),
                url+imageName+".jpg",
                imageName,
                camera
        );
        violationRepository.save(violation);
    }

    private void saveBase64ToFile(String base64Image, String outPath) {
        if (base64Image.contains(",")) {
            base64Image = base64Image.substring(base64Image.indexOf(",") + 1);
        }
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);

        File file = new File(outPath);
        file.getParentFile().mkdirs();

        try (OutputStream os = new FileOutputStream(file)) {
            os.write(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("이미지 저장 실패", e);
        }
    }

}
