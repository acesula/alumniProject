package com.alumni.project.service.friends;

import com.alumni.project.dal.entity.Friends;
import com.alumni.project.dal.entity.User;
import com.alumni.project.dal.repository.FriendsRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
@Transactional
public class FriendsServiceImpl implements FriendsService{
    private final FriendsRepository friendsRepository;
    private final UserRepository userRepository;


    private final UserServiceImpl userService;

    @Override
    public String save(String user1, String friend) {
        try{
            User user = userRepository.findByUsername(user1);
            User user2 = userRepository.findByUsername(friend);

            if(user != null && user2 != null ){
                if(this.areTheyAlreadyFriends(user.getId(),user2.getId())){
                    return "You are already friends";
                }
                Friends newFriend = new Friends(user, user2);
                friendsRepository.save(newFriend);
            }
            return "Success: Friend was added";
        } catch (Exception e){
            return e.getMessage();
        }
    }

    public boolean areTheyAlreadyFriends(UUID user1 , UUID friend1){

        List<Friends> totalListOfFriends = this.friendsRepository.findAll();
        Optional<Friends> friend = totalListOfFriends.stream().filter(friendItem ->
                (friendItem.getUser1().getId() == user1 && friendItem.getFriend().getId() == friend1) ||
                        (friendItem.getUser1().getId() == friend1 && friendItem.getFriend().getId() == user1)
        ).findFirst();
        if(friend.isEmpty()) return  false;
        return true;
    }



    @Override
    public List<Friends> findAll() {
        return friendsRepository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }


    @Override
    public List<Friends> findByUser(String username) {

        User user = userRepository.findByUsername(username);
        if(user != null){
//            List<Friends> listOfFriends = friendsRepository.
        }
          return null;

    }



    @Override
    public Friends findById(UUID id) {
        var optional = friendsRepository.findById(id);
        if (optional.isPresent()) {
            return map(optional.get());
        }
        throw new RuntimeException("No interest found");
    }

    @Override
    public void delete(UUID id) {
        this.friendsRepository.deleteById(id);

    }


    private Friends map(Friends friend) {
        var dto = new Friends();
        dto.setId(friend.getId());
        dto.setUser1(friend.getUser1());
        dto.setFriend(friend.getFriend());
        dto.setStartDate(friend.getStartDate());
        dto.setEndDate(friend.getEndDate());

        return dto;
    }
}
