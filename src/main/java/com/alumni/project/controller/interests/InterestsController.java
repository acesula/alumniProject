package com.alumni.project.controller.interests;

import com.alumni.project.dal.entity.Interests;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.service.interests.InterestsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/interests")
@RequiredArgsConstructor
public class InterestsController {

    private final InterestsServiceImpl interestsService;

    @PostMapping("/{username}")
    public ResponseEntity<ErrorResponse> save(@Valid @PathVariable String username, @RequestBody Interests interest){
        return interestsService.saveInterest(username, interest);
    }

    @GetMapping
    public List<Interests> findAll(){
        return interestsService.findAll();
    }

    @GetMapping("/{id}")
    public Interests findById(@PathVariable UUID id){
        return interestsService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id){
        interestsService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public Interests update(@PathVariable UUID id, @RequestBody Interests interest){
        return interestsService.update(id, interest);
    }

}
