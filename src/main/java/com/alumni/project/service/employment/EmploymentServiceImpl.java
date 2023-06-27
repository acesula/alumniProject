package com.alumni.project.service.employment;

import com.alumni.project.dal.entity.Employment;
import com.alumni.project.dal.entity.User;
import com.alumni.project.dal.repository.EmploymentRepository;
import com.alumni.project.dto.employment.EmploymentDto;
import com.alumni.project.dto.user.GetUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmploymentServiceImpl implements EmploymentService {

    private final EmploymentRepository employmentRepository;

    @Override
    public Employment save(Employment employment) {
        return employmentRepository.save(employment);
    }

    @Override
    public List<Employment> findAll() {
        return employmentRepository.findAll();
    }

    @Override
    public Employment findById(UUID id) {
        var optional = employmentRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new RuntimeException("Employment not found");
    }

    @Override
    public Employment update(UUID id, Employment employmentNew) {
        var employment = employmentRepository.findById(id).orElseThrow(RuntimeException::new);
        employment.setCompany(employmentNew.getCompany());
        employment.setJob(employmentNew.getJob());
        employment.setStartDate(employmentNew.getStartDate());
        employment.setEndDate(employmentNew.getEndDate());
        employment.setStatus(employmentNew.isStatus());

        return employmentRepository.save(employment);
    }

    @Override
    public void delete(UUID id) {
        employmentRepository.deleteById(id);
    }

}

