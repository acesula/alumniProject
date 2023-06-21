package com.alumni.project.controller;

import com.alumni.project.service.interests.InterestsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/interests")
@RequiredArgsConstructor
public class InterestsController {

    private final InterestsServiceImpl interestsService;
}
