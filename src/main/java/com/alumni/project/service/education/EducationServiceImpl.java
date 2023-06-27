package com.alumni.project.service.education;

import com.alumni.project.dal.entity.Education;
import com.alumni.project.dal.entity.Skills;
import com.alumni.project.dal.entity.User;
import com.alumni.project.dal.repository.EducationRepository;
import com.alumni.project.dal.repository.SkillsRepository;
import com.alumni.project.dto.user.GetUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {
    private final EducationRepository educationRepository;
    @Override
    public Education save(Education educationDto) {
        var education = new Education(
                educationDto.getInstitution(),
                educationDto.getDegree(),
                educationDto.getFieldOfStudy(),
                educationDto.getStartDate(),
                educationDto.getEndDate(),
                educationDto.isFinished()

        );
        return map(educationRepository.save(education));
    }

    @Override
    public List<Education> findAll() {
        return educationRepository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public Education findById(UUID id) {
        var optional = educationRepository.findById(id);
        if (optional.isPresent()) {
            return map(optional.get());
        }
        throw new RuntimeException("Education not found");
    }

    @Override
    public Education update(UUID id, Education dto) {
        var education = educationRepository.findById(id).orElseThrow(RuntimeException::new);
        education.setInstitution(education.getInstitution());
        education.setDegree(education.getDegree());
        education.setFieldOfStudy(education.getFieldOfStudy());
        education.setStartDate(education.getStartDate());
        education.setEndDate(education.getEndDate());
        education.setFinished(education.isFinished());


        return map(educationRepository.save(education));
    }

    @Override
    public void delete(UUID id) {
        educationRepository.deleteById(id);

    }
    private Education map(Education education) {
        var dto = new Education();
        dto.setId(education.getId());
        dto.setInstitution(education.getInstitution());
        dto.setDegree(education.getDegree());
        dto.setFieldOfStudy(education.getFieldOfStudy());
        dto.setStartDate(education.getStartDate());
        dto.setEndDate(education.getEndDate());
        dto.setFinished(education.isFinished());

        return dto;
    }
}
