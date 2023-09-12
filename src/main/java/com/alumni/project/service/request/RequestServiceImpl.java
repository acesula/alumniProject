package com.alumni.project.service.request;

import com.alumni.project.dal.entity.Request;
import com.alumni.project.dal.entity.User;
import com.alumni.project.dal.repository.RequestRepository;
import com.alumni.project.dal.repository.UserRepository;

import com.alumni.project.dto.user.UserDto;
import com.alumni.project.dto.user.UserInfoDto;
import com.alumni.project.dto.user.UserRequestDto;
import com.alumni.project.service.friends.FriendsServiceImpl;
import com.alumni.project.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RequestServiceImpl implements RequestService {

    private final UserRepository userRepository;
    private final RequestRepository requestRepository;

    private final FriendsServiceImpl friendsService;
    private final UserServiceImpl userService;


    public String sendRequest(String sender, String receiver) {

       try {
           System.out.println(sender);
           System.out.println(receiver);
           User senderUser = userRepository.findByUsername(sender);
           User receiverUser = userRepository.findByUsername(receiver);

           if(senderUser != null && receiverUser != null ){

               if(this.isRequestSentBefore(senderUser.getId(),receiverUser.getId())){
                   return "You have already sent request";
               }
               Request newRequest = new Request(senderUser, receiverUser, RequestStatus.PENDING.toString());
               requestRepository.save(newRequest);
           }

           return "Success: Request was sent";
       } catch (Exception e){
           return e.getMessage();
       }
    }




    public boolean isRequestSentBefore(UUID senderId, UUID receiverId){

        List<Request> totalListOfRequests = this.requestRepository.findAll();
        Optional<Request> request = totalListOfRequests.stream().filter(requestItem ->
                (requestItem.getUser1().getId() == senderId && requestItem.getUser2().getId() == receiverId) ||
                        (requestItem.getUser1().getId() == receiverId && requestItem.getUser2().getId() == senderId)
        ).findFirst();
        return request.isPresent();
    }
    @Override
    public List<UserRequestDto> findAllByUsername(String username) {

//        User user = userRepository.findByUsername(username);
//        return requestRepository.findAllByUsername(user.getUsername());
        try {
            List<UserRequestDto> result = this.requestRepository.findAllByUsername(username).stream().toList();
            if(!result.isEmpty()){
                return  result;
            }
        } catch (Exception e){
            System.out.println("----error in getting user info"+ e.getMessage());
        }
        return null;
   }



    @Override
    public Request findById(UUID id) {
        var optional = requestRepository.findById(id);
        if (optional.isPresent()) {
            return map(optional.get());
        }
        throw new RuntimeException("No interest found");
    }

<<<<<<< HEAD
    @Override
    public void delete(UUID id) {
        this.requestRepository.deleteById(id);
    }
=======
//    @Override
//    public void deleteByUsername(UUID id) {
//        UserDto user = this.userService.findById(id);
//        this.userService.delete(user.getId());
//    }
>>>>>>> 6ef8658e2fe6a08ac60418cd7b077e0b96c20368


    @Override
    public Request update(UUID id, Request request, String newStatus) {
        var req = requestRepository.findById(id).orElseThrow(RuntimeException::new);
        req.setStatus(newStatus);
        if(Objects.equals(newStatus, RequestStatus.APPROVED.toString())){
            //Call Friends method for create
            this.friendsService.save(req.getUser1().getUsername(), req.getUser2().getUsername());
        }
        return requestRepository.save(req);
    }

    private Request map(Request request) {
        var dto = new Request();
        dto.setUser1(request.getUser1());
        dto.setUser2(request.getUser2());
        dto.setStatus(request.getStatus());
        dto.setStartDate(request.getStartDate());
        dto.setEndDate(request.getEndDate());

        return dto;
    }


//    public List<Request> findAll() {
//    }
}
