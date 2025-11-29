package com.hospital.hms.sercive.impl;

import com.hospital.hms.sercive.MedicalDeptService;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MedicalDeptServiceImplTest {
@Autowired
MedicalDeptService medicalDeptService;
    @Test
    void deleteMedicalDeptById() {
        Integer[] ids = {14,15};
        medicalDeptService.deleteMedicalDeptById(ids);

    }
}