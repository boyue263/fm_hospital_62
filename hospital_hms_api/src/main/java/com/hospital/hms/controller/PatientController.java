package com.hospital.hms.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @GetMapping("/list")
    public String patientList(Model model) {
        model.addAttribute("title", "患者列表");
        return "patient/list";
    }

    @GetMapping("/add")
    public String addPatientForm(Model model) {
        model.addAttribute("title", "添加患者");
        return "patient/add";
    }
}