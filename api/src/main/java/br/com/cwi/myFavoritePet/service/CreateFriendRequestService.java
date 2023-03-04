package br.com.cwi.myFavoritePet.service;

import br.com.cwi.myFavoritePet.controller.response.IdResponse;
import br.com.cwi.myFavoritePet.domain.FriendRequest;
import br.com.cwi.myFavoritePet.repository.FriendRequestRepository;
import br.com.cwi.myFavoritePet.security.domain.Users;
import br.com.cwi.myFavoritePet.security.service.AuthenticatedUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.cwi.myFavoritePet.mapper.IdResponseMapper.toResponse;

@Service
public class CreateFriendRequestService {

    @Autowired
    private AuthenticatedUsersService authenticatedUsersService;

    @Autowired
    private SearchUsersService searchUsersService;

    @Autowired
    private FriendRequestRepository friendRequestRepository;

    public IdResponse create(Long userId) {

        Users user = authenticatedUsersService.get();
        Users userToAdd = searchUsersService.byId(userId);

        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setSenderId(user);
        friendRequest.setUsersId(userToAdd);

        friendRequestRepository.save(friendRequest);

        return toResponse(friendRequest.getId());
    }
}
