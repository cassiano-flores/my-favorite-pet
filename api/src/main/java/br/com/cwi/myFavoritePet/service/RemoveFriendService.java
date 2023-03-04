package br.com.cwi.myFavoritePet.service;

import br.com.cwi.myFavoritePet.security.domain.Users;
import br.com.cwi.myFavoritePet.security.repository.UsersRepository;
import br.com.cwi.myFavoritePet.security.service.AuthenticatedUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoveFriendService {

    @Autowired
    private AuthenticatedUsersService authenticatedUsersService;

    @Autowired
    private SearchUsersService searchUsersService;

    @Autowired
    private UsersRepository usersRepository;

    public void remove(Long userId) {

        Users user = authenticatedUsersService.get();
        Users userToRemove = searchUsersService.byId(userId);

        user.getFriendships().remove(userToRemove);
        userToRemove.getFriendships().remove(user);

        usersRepository.save(user);
        usersRepository.save(userToRemove);
    }
}
