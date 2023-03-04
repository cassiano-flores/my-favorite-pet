package br.com.cwi.myFavoritePet.service.validations;

import br.com.cwi.myFavoritePet.security.domain.Users;
import br.com.cwi.myFavoritePet.security.service.AuthenticatedUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ValidateAcceptFriendRequestService {

    @Autowired
    private AuthenticatedUsersService authenticatedUsersService;

    public void validate(Long userId) {

        Users userMe = authenticatedUsersService.get();

        boolean containsUserToAccept = userMe.getFriendRequests().stream()
                .anyMatch(request -> request.getSenderId().getId().equals(userId));

        if (!containsUserToAccept) {
            throw new ResponseStatusException(NOT_FOUND, "User not found in friend requests");
        }
    }
}
