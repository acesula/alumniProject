package com.alumni.project.dto.user;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

public interface UserInfoDto {

    UUID getId();
    String getUsername();
    String getEmail();
    String getSkillDescription();
    String getSkillField();
//    List<SkillDto> getSk();
    interface SkillDto {
        String getSkillDescription();
        String getSkillField();
    }
}



