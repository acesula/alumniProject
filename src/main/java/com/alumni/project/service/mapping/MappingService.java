package com.alumni.project.service.mapping;

import com.alumni.project.dal.entity.*;
import com.alumni.project.dto.Chat.ChatDto;
import com.alumni.project.dto.announcements.AnnouncementsDto;
import com.alumni.project.dto.chatRoom.ChatRoomDto;
import com.alumni.project.dto.connection.UserConnectionDto;
import com.alumni.project.dto.contactdetails.ContactDetailsDto;
import com.alumni.project.dto.education.EducationDto;
import com.alumni.project.dto.employment.EmploymentDto;
import com.alumni.project.dto.event.AttendeesDto;
import com.alumni.project.dto.event.EventDto;
import com.alumni.project.dto.friends.FriendsDto;
import com.alumni.project.dto.interests.InterestsDto;
import com.alumni.project.dto.request.RequestDto;
import com.alumni.project.dto.skills.SkillsDto;
import com.alumni.project.dto.user.AdminUserInfoDto;
import com.alumni.project.dto.user.RegisterDto;
import com.alumni.project.dto.user.UpdatePersonalInfoDto;
import com.alumni.project.dto.user.UserDto;

public interface MappingService {

    //User
    UserDto convertToUserDto(User user);

    User convertToUser(UserDto userDto);

    //Announcements
    AnnouncementsDto convertToAnnouncementsDto(Announcements announcements);

    Announcements convertToAnnouncements(AnnouncementsDto announcementsDto);

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

    //ChatRoom
    ChatRoomDto convertToChatRoomDto(ChatRoom chatRoom);

    ChatRoom convertToChatRoom(ChatRoomDto chatRoomDto);

    //Chat
    ChatDto convertToChatDto(Chat chat);
    Chat convertToChat(ChatDto chatDto);

    //UpdatePersonalInfoDto
    UpdatePersonalInfoDto convertToUpdatePersonalInfoDto(User user);

    //Register
    RegisterDto convertToRegisterDto(User user);

    User convertToUser(RegisterDto registerDto);

    //Attendees
    AttendeesDto convertToAttendeesDto(Attendees attendees);

    Attendees convertToAttendees(AttendeesDto attendeesDto);

    //Friends
    FriendsDto convertToFriendsDto(Friends friends);

    Friends convertToFriends(FriendsDto friendsDto);

    //Requests
    RequestDto convertToRequestDto(Request request);

    Request convertToRequest(RequestDto requestDto);

    //Admin
    AdminUserInfoDto convertToAdminUserInfoDto(User user);

    User convertToUser(AdminUserInfoDto adminUserInfoDto);


}

