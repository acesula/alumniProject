package com.alumni.project.service.skills;

import com.alumni.project.dal.entity.Skills;
import com.alumni.project.dal.entity.User;
import com.alumni.project.dal.repository.SkillsRepository;
import com.alumni.project.dto.user.GetUserDto;
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
                    skills.getSkillField(),
                    skills.getSkillDescription()

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
        throw new RuntimeException("Skill not found");
    }


    @Override
    public Skills update(UUID id, Skills skillDto) {

        var skill = skillsRepository.findById(id).orElseThrow(RuntimeException::new);
        skill.setSkillField(skillDto.getSkillField());
        skill.setSkillField(skillDto.getSkillField());


        return map(skillsRepository.save(skill));
    }

    @Override
    public void delete(UUID id) {
        skillsRepository.deleteById(id);
    }
    private Skills map(Skills skills) {
        var dto = new Skills();
        dto.setId(skills.getId());
        dto.setSkillField(skills.getSkillField());
        dto.setSkillDescription(skills.getSkillDescription());

        return dto;
    }
}
