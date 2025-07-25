package com.website.repository;

import com.website.entity.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CameraRepository extends JpaRepository<Camera, String> {

    int countByStatus(String on);

}
