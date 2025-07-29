package com.website.camera;

import com.website.entity.Camera;
import com.website.user.dto.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CameraController {
    private final CameraService cameraService;

    public CameraController(CameraService cameraService) {
        this.cameraService = cameraService;
    }

    @GetMapping("/api/health")
    public ResponseEntity<?> healthCheck(){
        return ResponseEntity.ok().body("건강합니다.");
    }
    @GetMapping("/auth/camera")
    public ResponseEntity<List<Camera>> getCamera(){
        return ResponseEntity.ok().body(cameraService.getCamera());
    }

    @GetMapping("/auth/streaming")
    public ResponseEntity<String> getStreamingURL(@AuthenticationPrincipal CustomUserDetails user){
        return ResponseEntity.ok().body("rtsp://진혁이 주소");
    }
}
