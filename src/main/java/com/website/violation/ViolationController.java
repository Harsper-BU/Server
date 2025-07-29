package com.website.violation;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/violations")
public class ViolationController {
    private final ViolationService violationService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public ViolationController(ViolationService violationService, SimpMessagingTemplate simpMessagingTemplate) {
        this.violationService = violationService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @GetMapping
    public ResponseEntity<Page<ViolationMainPageDto>> getViolationDataByPage(@RequestParam int page){
        return ResponseEntity.ok().body(violationService.getViolationDataByPage(page));
    }

    @PostMapping    //뭐 받지?
    public ResponseEntity<String> insertViolationData(@RequestBody ViolationDto violationDto){
        System.out.println("왔다");
        System.out.println(violationDto);
        simpMessagingTemplate.convertAndSend("/sub/violation-alert", violationDto);
        violationService.insertViolationData(violationDto);
        return ResponseEntity.ok().body("ㅋㅋ성공했노");
    }
}
