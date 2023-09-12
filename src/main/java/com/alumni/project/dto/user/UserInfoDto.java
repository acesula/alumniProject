package com.alumni.project.dto.user;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface UserInfoDto {

    UUID getId();
    String getUsername();
    String getEmail();
    String getSkillDescription();
    String getSkillField();
    String getInterestDescription();
    String getCompany();
    String getJob();
    LocalDate getStartDate();
    String getInstitution();
    String getDegree();
    String getFieldOfStudy();
    LocalDate getEndDate();

//    String getPhoneNumber();
//    String getCountry();
//    String getCity();
//    String getAddress();
//    String getLinkedIn();

    interface SkillDto {
        String getSkillDescription();
        String getSkillField();

    }
    interface InterestDto{
         String getInterestDescription() ;
    }
    interface Employment{
        String getCompany();
        String getJob();
        LocalDate getStartDate();
        LocalDate getEndDate();

    }

    interface Education{
         String getInstitution();
         String getDegree();
        String getFieldOfStudy();
        LocalDate getStartDate();
         LocalDate getEndDate();
    }
//    interface ContactDetails{
//         String getPhoneNumber();
//         String getCountry();
//         String getCity();
//         String getAddress();
//        String getLinkedIn();
//    }
}



