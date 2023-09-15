package com.alumni.project.service.request;

import com.alumni.project.dal.entity.Request;
import com.alumni.project.dal.repository.RequestRepository;
import com.alumni.project.dal.repository.UserRepository;

import com.alumni.project.dto.request.RequestDto;
import com.alumni.project.dto.user.UserRequestDto;
import com.alumni.project.service.friends.FriendsServiceImpl;
import com.alumni.project.service.mapping.MappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RequestServiceImpl implements RequestService {

    private final UserRepository userRepository;
    private final RequestRepository requestRepository;

    private final FriendsServiceImpl friendsService;
    private final MappingService mappingService;


    public void sendRequest(UUID id1, UUID id2) {

        try {

            var senderUser = userRepository.findById(id1).orElseThrow(RuntimeException::new);
            var receiverUser = userRepository.findById(id2).orElseThrow(RuntimeException::new);

            if (senderUser != null && receiverUser != null) {

                if (isRequestSentBefore(senderUser.getId(), receiverUser.getId())) {
                    return;
                }
                if (friendsService.areTheyAlreadyFriends(senderUser.getId(), receiverUser.getId())) {
                    return;
                }

                Request newRequest = new Request(senderUser, receiverUser);
                requestRepository.save(newRequest);
            }

        } catch (Exception e) {
            e.getMessage();
        }
    }


    public boolean isRequestSentBefore(UUID senderId, UUID receiverId) {

        List<Request> totalListOfRequests = requestRepository.findAll();
        Optional<Request> request = totalListOfRequests.stream().filter(requestItem ->
                (requestItem.getUser1().getId() == senderId && requestItem.getUser2().getId() == receiverId) ||
                        (requestItem.getUser1().getId() == receiverId && requestItem.getUser2().getId() == senderId)
        ).findFirst();
        return request.isPresent();
    }

    @Override
    public List<UserRequestDto> findAllById(UUID id) {

        try {
            List<UserRequestDto> result = this.requestRepository.findAllById(id).stream().toList();
            if (!result.isEmpty()) {
                return result;
            }
        } catch (Exception e) {
            System.out.println("----error in getting user info" + e.getMessage());
        }
        return null;
    }


    @Override
    public RequestDto findById(UUID id) {
        var optional = requestRepository.findById(id);
        if (optional.isPresent()) {
            return mappingService.convertToRequestDto(optional.get());
        }
        throw new RuntimeException("No interest found");
    }


    @Override
    public void delete(UUID id) {
        requestRepository.deleteById(id);
    }


    @Override
    public void acceptRequest(UUID id) {
        var req = requestRepository.findById(id).orElseThrow(RuntimeException::new);
        friendsService.save(req.getUser1().getId(), req.getUser2().getId());
        requestRepository.delete(req);
    }

}
