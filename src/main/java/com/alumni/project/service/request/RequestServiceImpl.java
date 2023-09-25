package com.alumni.project.service.request;

import com.alumni.project.dal.entity.Request;
import com.alumni.project.dal.repository.RequestRepository;
import com.alumni.project.dal.repository.UserRepository;

import com.alumni.project.dto.request.RequestDto;
import com.alumni.project.dto.request.UserRequestDto;
import com.alumni.project.security.model.AuthUserDetail;
import com.alumni.project.service.friends.FriendsServiceImpl;
import com.alumni.project.service.mapping.MappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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


    public AuthUserDetail authenticatedUser() {
        return (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public void sendRequest(UUID id) {

        try {

            var senderUser = userRepository.findById(authenticatedUser().getId()).orElseThrow(RuntimeException::new);
            var receiverUser = userRepository.findById(id).orElseThrow(RuntimeException::new);

            if (senderUser != null && receiverUser != null) {

                if (isRequestSentBefore(receiverUser.getId())) {
                    return;
                }
                if (friendsService.areTheyAlreadyFriends(receiverUser.getId())) {
                    return;
                }

                Request newRequest = new Request(senderUser, receiverUser);
                requestRepository.save(newRequest);
            }

        } catch (Exception e) {
            e.getMessage();
        }
    }


    public boolean isRequestSentBefore(UUID receiverId) {

        List<Request> totalListOfRequests = requestRepository.findAll();
        Optional<Request> request = totalListOfRequests.stream().filter(requestItem ->
                (requestItem.getUser1().getId() == authenticatedUser().getId() && requestItem.getUser2().getId() == receiverId) ||
                        (requestItem.getUser1().getId() == receiverId && requestItem.getUser2().getId() == authenticatedUser().getId())
        ).findFirst();
        return request.isPresent();
    }

    @Override
    public List<UserRequestDto> findAllById() {

        try {
            List<UserRequestDto> result = requestRepository.findAllById(authenticatedUser().getId()).stream().toList();
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
        friendsService.save(req.getUser2().getId());
        requestRepository.delete(req);
    }

}
