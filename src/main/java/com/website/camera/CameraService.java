package com.website.camera;

import com.website.entity.Camera;
import com.website.repository.CameraRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CameraService {
    private final CameraRepository cameraRepository;
    private final RestTemplate restTem;
    public CameraService(CameraRepository cameraRepository, RestTemplate restTem) {
        this.cameraRepository = cameraRepository;
        this.restTem = restTem;
    }

    public List<Camera> getCamera() {
        return cameraRepository.findAll();
    }

    @Scheduled(fixedRate = 5 * 60 * 1000)
    @Transactional
    public void checkAllCamera(){
        List<Camera> cameras = cameraRepository.findAll();
        for(Camera c : cameras){
            try {
                ResponseEntity<String> res = restTem.getForEntity(c.getIpAddress(), String.class);
                c.setStatus(res.getStatusCode().is2xxSuccessful() ? "on" : "off");
                c.setLastUpdate(LocalDateTime.now());
            } catch (Exception e){
                System.out.println(c.getDeviceId()+" 카메라와 연결을 실패했습니다.");
                System.out.println(e.getMessage());
                c.setStatus("off");
                c.setLastUpdate(LocalDateTime.now());
            }
        }
        cameraRepository.saveAll(cameras);
    }

}
