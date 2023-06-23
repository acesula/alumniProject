package com.alumni.project.controller.employment;

import com.alumni.project.dal.entity.Employment;
import com.alumni.project.dto.employment.EmploymentDto;
import com.alumni.project.service.employment.EmploymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/employment")
@RequiredArgsConstructor
public class EmploymentController {

    private final EmploymentServiceImpl employmentService;

    @PostMapping
    public Employment save(@RequestBody Employment employment){
        return employmentService.save(employment);
    }

    @GetMapping
    public List<Employment> findAll(){
        return employmentService.findAll();
    }

    @GetMapping("/{id}")
    public Employment findById(@PathVariable UUID id){
        return employmentService.findById(id);
    }

    @PatchMapping("/{id}")
    public Employment update(@PathVariable UUID id,@RequestBody Employment employment){
        return employmentService.update(id, employment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        employmentService.delete(id);
    }
}
