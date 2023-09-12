package com.alumni.project.service.mapping;

import com.alumni.project.dal.entity.*;
import com.alumni.project.dto.Chat.ChatDto;
import com.alumni.project.dto.announcements.AnnouncementsDto;
import com.alumni.project.dto.chatRoom.ChatRoomDto;
import com.alumni.project.dto.connection.UserConnectionDto;
import com.alumni.project.dto.contactdetails.ContactDetailsDto;
import com.alumni.project.dto.education.EducationDto;
import com.alumni.project.dto.employment.EmploymentDto;
import com.alumni.project.dto.event.EventDto;
import com.alumni.project.dto.interests.InterestsDto;
import com.alumni.project.dto.skills.SkillsDto;
import com.alumni.project.dto.user.UserDto;

public interface MappingService {

    //User
    UserDto convertToUserDto(User user);

    User convertToUser(UserDto userDto);

    //Announcements
    AnnouncementsDto convertToAnnouncementsDto(Announcements announcements);

    Announcements convertToAnnouncements(AnnouncementsDto announcementsDto);

    //Connection
    UserConnectionDto convertToUserConnectionDto(UserConnection userConnection);

    UserConnection convertToUserConnection(UserConnectionDto userConnectionDto);

    //ContactDetails
    ContactDetailsDto convertToContactDetailsDto(ContactDetails contactDetails);

    ContactDetails convertToContactDetails(ContactDetailsDto contactDetailsDto);

    //Education
    EducationDto convertToEducationDto(Education education);

    Education convertToEducation(EducationDto educationDto);

    //Employment
    EmploymentDto convertToEmploymentDto(Employment employment);

    Employment convertToEmployment(EmploymentDto employmentDto);

    //Event
    EventDto convertToEventDto(Event event);

    Event convertToEvent(EventDto eventDto);

    //Skills
    SkillsDto convertToSkillsDto(Skills skills);

    Skills convertToSkills(SkillsDto skillsDto);

    //Interests
    InterestsDto convertToInterestsDto(Interests interests);

    Interests convertToInterests(InterestsDto interestsDto);

    ChatRoomDto convertToChatRoomDto(ChatRoom chatRoom);

    ChatRoom convertToChatRoom(ChatRoomDto chatRoomDto);

    ChatDto convertToChatDto(Chat chat);
    Chat convertToChat(ChatDto chatDto);


}

