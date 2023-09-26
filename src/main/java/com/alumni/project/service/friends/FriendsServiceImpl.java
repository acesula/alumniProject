package com.alumni.project.service.friends;

import com.alumni.project.dal.entity.Friends;
import com.alumni.project.dal.repository.FriendsRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.friends.FriendsDto;
import com.alumni.project.dto.friends.GetFriendsDto;
import com.alumni.project.security.model.AuthUserDetail;
import com.alumni.project.service.mapping.MappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class FriendsServiceImpl implements FriendsService {
    private final FriendsRepository friendsRepository;
    private final UserRepository userRepository;
    private final MappingService mappingService;

    public AuthUserDetail authenticatedUser() {
        return (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    @Transactional
    public String save(UUID id) {
        try {
            var user = userRepository.findById(authenticatedUser().getId()).orElseThrow(RuntimeException::new);
            var user2 = userRepository.findById(id).orElseThrow(RuntimeException::new);

            if (user != null && user2 != null) {
                if (areTheyAlreadyFriends(user2.getId())) {
                    return "You are already friends";
                }
                Friends newFriend = new Friends(user, user2);
                friendsRepository.save(newFriend);
            }
            return "Success: Friend was added";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public List<GetFriendsDto> findAllFriendsPerUser() {
        return friendsRepository.findAllFriendsPerUser(authenticatedUser().getId());
    }

    public boolean areTheyAlreadyFriends(UUID friend) {

        return friendsRepository.areTheyAlreadyFriends(authenticatedUser().getId(), friend);
    }


    @Override
    public FriendsDto findById(UUID id) {
        var optional = friendsRepository.findById(id);
        if (optional.isPresent()) {
            return mappingService.convertToFriendsDto(optional.get());
        }
        throw new RuntimeException("No interest found");
    }

    @Override
    public void delete(UUID id) {
        this.friendsRepository.deleteById(id);

    }


}
