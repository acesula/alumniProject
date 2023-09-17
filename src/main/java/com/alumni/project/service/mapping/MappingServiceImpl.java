package com.alumni.project.service.mapping;

import com.alumni.project.dal.entity.*;
import com.alumni.project.dto.Chat.ChatDto;
import com.alumni.project.dto.announcements.AnnouncementsDto;
import com.alumni.project.dto.chatRoom.ChatRoomDto;
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
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MappingServiceImpl implements MappingService {

    private final ModelMapper modelMapper;


    @Override
    public UserDto convertToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User convertToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    @Override
    public AnnouncementsDto convertToAnnouncementsDto(Announcements announcements) {
        return modelMapper.map(announcements, AnnouncementsDto.class);
    }

    @Override
    public Announcements convertToAnnouncements(AnnouncementsDto announcementsDto) {
        return modelMapper.map(announcementsDto, Announcements.class);
    }

    @Override
    public ContactDetailsDto convertToContactDetailsDto(ContactDetails contactDetails) {
        return modelMapper.map(contactDetails, ContactDetailsDto.class);
    }

    @Override
    public ContactDetails convertToContactDetails(ContactDetailsDto contactDetailsDto) {
        return modelMapper.map(contactDetailsDto, ContactDetails.class);
    }

    @Override
    public EducationDto convertToEducationDto(Education education) {
        return modelMapper.map(education, EducationDto.class);
    }

    @Override
    public Education convertToEducation(EducationDto educationDto) {
        return modelMapper.map(educationDto, Education.class);
    }

    @Override
    public EmploymentDto convertToEmploymentDto(Employment employment) {
        return modelMapper.map(employment, EmploymentDto.class);
    }

    @Override
    public Employment convertToEmployment(EmploymentDto employmentDto) {
        return modelMapper.map(employmentDto, Employment.class);
    }

    @Override
    public EventDto convertToEventDto(Event event) {
        return modelMapper.map(event, EventDto.class);
    }

    @Override
    public Event convertToEvent(EventDto eventDto) {
        return modelMapper.map(eventDto, Event.class);
    }

    @Override
    public SkillsDto convertToSkillsDto(Skills skills) {
        return modelMapper.map(skills, SkillsDto.class);
    }

    @Override
    public Skills convertToSkills(SkillsDto skillsDto) {
        return modelMapper.map(skillsDto, Skills.class);
    }

    @Override
    public InterestsDto convertToInterestsDto(Interests interests) {
        return modelMapper.map(interests, InterestsDto.class);
    }

    @Override
    public Interests convertToInterests(InterestsDto interestsDto) {
        return modelMapper.map(interestsDto, Interests.class);
    }

    @Override

    public ChatRoomDto convertToChatRoomDto(ChatRoom chatRoom) {
        return modelMapper.map(chatRoom, ChatRoomDto.class);
    }

    @Override
    public ChatRoom convertToChatRoom(ChatRoomDto chatRoomDto) {
        return modelMapper.map(chatRoomDto, ChatRoom.class);
    }

    @Override
    public ChatDto convertToChatDto(Chat chat) {
        return modelMapper.map(chat, ChatDto.class);
    }

    @Override
    public Chat convertToChat(ChatDto chatDto) {
        return modelMapper.map(chatDto, Chat.class);

    }

    @Override
    public UpdatePersonalInfoDto convertToUpdatePersonalInfoDto(User user) {
        return modelMapper.map(user, UpdatePersonalInfoDto.class);
    }

    @Override
    public RegisterDto convertToRegisterDto(User user) {
        return modelMapper.map(user, RegisterDto.class);
    }

    @Override
    public User convertToUser(RegisterDto registerDto) {
        return modelMapper.map(registerDto, User.class);
    }

    @Override
    public AttendeesDto convertToAttendeesDto(Attendees attendees) {
        return modelMapper.map(attendees, AttendeesDto.class);
    }

    @Override
    public Attendees convertToAttendees(AttendeesDto attendeesDto) {
        return modelMapper.map(attendeesDto, Attendees.class);
    }

    @Override
    public FriendsDto convertToFriendsDto(Friends friends) {
        return modelMapper.map(friends, FriendsDto.class);
    }

    @Override
    public Friends convertToFriends(FriendsDto friendsDto) {
        return modelMapper.map(friendsDto, Friends.class);
    }

    @Override
    public RequestDto convertToRequestDto(Request request) {
        return modelMapper.map(request, RequestDto.class);
    }

    @Override
    public Request convertToRequest(RequestDto requestDto) {
        return modelMapper.map(requestDto, Request.class);
    }

    @Override
    public AdminUserInfoDto convertToAdminUserInfoDto(User user) {
        return modelMapper.map(user, AdminUserInfoDto.class);
    }

    @Override
    public User convertToUser(AdminUserInfoDto adminUserInfoDto) {
        return modelMapper.map(adminUserInfoDto, User.class);
    }
}
