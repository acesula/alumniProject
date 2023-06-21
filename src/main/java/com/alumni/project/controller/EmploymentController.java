package com.alumni.project.controller;

import com.alumni.project.service.employment.EmploymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/employment")
@RequiredArgsConstructor
public class EmploymentController {

    private final EmploymentServiceImpl employmentService;
}
