package com.alumni.project.controller.employment;

import com.alumni.project.dal.entity.Employment;
import com.alumni.project.dto.employment.EmploymentDto;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.security.model.AuthUserDetail;
import com.alumni.project.service.employment.EmploymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employment")
@RequiredArgsConstructor
public class EmploymentController {

    private final EmploymentService employmentService;

    public AuthUserDetail authenticatedUser() {
        return (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping
    public ResponseEntity<ErrorResponse> save(@RequestBody Employment employment){
        return employmentService.saveEmployment(authenticatedUser().getId(),employment);
    }

//    @GetMapping
//    public List<EmploymentDto> findAll(){
//        return employmentService.findAll();
//    }

    @GetMapping
    public List<EmploymentDto> findByUserId(){
        return employmentService.findByUserId(authenticatedUser().getId());
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
