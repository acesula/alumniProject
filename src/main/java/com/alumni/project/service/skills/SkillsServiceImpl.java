package com.alumni.project.service.skills;

import com.alumni.project.dal.entity.Skills;
import com.alumni.project.dal.repository.SkillsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillsServiceImpl implements SkillsService{
    private final SkillsRepository skillsRepository;


    @Override
    public Skills save(Skills skills) {
            var skill = new Skills(
                    Skills.getSkillField(),
                    Skills.getSkillDescription()

            );

            var saved = skillsRepository.save(skill);
            return map(saved);

    }

    @Override
    public List<Skills> findAll() {

        return skillsRepository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public Skills findById(UUID id) {

        var optional = skillsRepository.findById(id);
        if (optional.isPresent()) {
            return map(optional.get());
        }
        throw new RuntimeException("User not found");
    }

    @Override
    public Skills update(UUID id, Skills dto) {

        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
