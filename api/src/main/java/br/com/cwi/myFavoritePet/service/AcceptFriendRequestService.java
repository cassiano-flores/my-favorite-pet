package br.com.cwi.myFavoritePet.service;

import br.com.cwi.myFavoritePet.repository.FriendRequestRepository;
import br.com.cwi.myFavoritePet.security.domain.Users;
import br.com.cwi.myFavoritePet.security.repository.UsersRepository;
import br.com.cwi.myFavoritePet.security.service.AuthenticatedUsersService;
import br.com.cwi.myFavoritePet.service.validations.ValidateAcceptFriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AcceptFriendRequestService {

    @Autowired
    private ValidateAcceptFriendRequestService validateAcceptFriendRequestService;

    @Autowired
    private AuthenticatedUsersService authenticatedUsersService;

    @Autowired
    private SearchUsersService searchUsersService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Transactional
    public void accept(Long userId) {
        validateAcceptFriendRequestService.validate(userId);

        Users userMe = authenticatedUsersService.get();
        Users userToAccept = searchUsersService.byId(userId);

        userMe.getFriendships().add(userToAccept);
        userToAccept.getFriendships().add(userMe);

        friendRequestRepository.deleteBySenderId(userToAccept);

        usersRepository.save(userMe);
        usersRepository.save(userToAccept);
    }
}
