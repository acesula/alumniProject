package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.User;
import com.alumni.project.dto.user.UserDto;
import com.alumni.project.dto.user.UserInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {
    @Transactional
    @Modifying
    @Query("update User u set u.profilePicture = ?1 where u.id = ?2")
    void updateProfilePictureById(String profilePicture, UUID id);
    long deleteByUsername(String username);
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);



    @Query(
            value = "select u.id,u.username, u.email, sk.skill_field as skillField, sk.skill_description as skillDescription," +
                    " i.interest_description as interestDescription, em.company as company, " +
                    "em.job as job, em.start_date as startDate, em.end_date as endDate," +
                    "ed.institution as institution, ed.degree as degree, ed.field_of_study as fieldOfStudy" +
                    " from users_table u \n" +
                    "left join skills sk on u.id = sk.user_id\n" +
                    "left join interests i on u.id = i.user_id\n" +
                    "left join employment em on u.id = em.user_id\n" +
                    "left join education ed on u.id = ed.user_id\n" +
                    "where u.username = 'doni'",
            nativeQuery = true)
    List<UserInfoDto> getUserInfoByUsername(String username);





    //, s.skill_description as skillDescription, s.skill_field as skillField
//    where u.username = 'doni'"
//    List<UserInfoDto>

}
