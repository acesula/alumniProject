package com.alumni.project.service.request;

import com.alumni.project.dal.entity.Request;
import com.alumni.project.dal.entity.User;
import com.alumni.project.dal.repository.RequestRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.user.GetUserDto;
import com.alumni.project.service.Friends.FriendsServiceImpl;
import com.alumni.project.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final UserRepository userRepository;
    private final RequestRepository requestRepository;

    @Autowired
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
        if(request.isEmpty()) return  false;
        return true;
    }
    @Override
    public List<Request> findAllByUsername(String username) {
        return requestRepository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<Request> findByUser(String username) {
//        return requestRepository.findByUser_Username(username)
//                .stream()
//                .map(this::map)
//                .collect(Collectors.toList());

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

    @Override
    public void deleteByUsername(UUID id) {
        GetUserDto user = this.userService.findById(id);
        this.userService.delete(user.getUsername());
    }


    @Override
    public Request update(UUID id, Request request, String newStatus) {
        var req = requestRepository.findById(id).orElseThrow(RuntimeException::new);
        req.setStatus(newStatus);
        if(newStatus == RequestStatus.APPROVED.toString()){
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


}
