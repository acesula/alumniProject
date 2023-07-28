package com.alumni.project.controller.employment;

import com.alumni.project.dal.entity.Employment;
import com.alumni.project.dto.employment.EmploymentDto;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.service.employment.EmploymentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/employment")
@RequiredArgsConstructor
public class EmploymentController {

    private final EmploymentServiceImpl employmentService;

    @PostMapping("/{username}")
    public ResponseEntity<ErrorResponse> save(@Valid @PathVariable String username, @RequestBody Employment employment){
        return employmentService.saveEmployment(username,employment);
    }

    @GetMapping
    public List<EmploymentDto> findAll(){
        return employmentService.findAll();
    }

    @GetMapping("/{id}")
    public EmploymentDto findById(@PathVariable UUID id){
        return employmentService.findById(id);
    }

    @GetMapping("/{username}")
    public List<EmploymentDto> findByUser(@PathVariable String username){
        return employmentService.findByUser(username);
    }

    @PatchMapping("/{id}")
    public EmploymentDto update(@PathVariable UUID id,@RequestBody EmploymentDto employmentDto){
        return employmentService.update(id, employmentDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        employmentService.delete(id);
    }
}
