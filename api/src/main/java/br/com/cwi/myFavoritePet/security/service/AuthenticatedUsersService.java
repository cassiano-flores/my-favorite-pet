package br.com.cwi.myFavoritePet.security.service;

import br.com.cwi.myFavoritePet.security.UsersSecurity;
import br.com.cwi.myFavoritePet.security.domain.Users;
import br.com.cwi.myFavoritePet.security.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
public class AuthenticatedUsersService {

    @Autowired
    private UsersRepository usersRepository;

    public Long getId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersSecurity usersSecurity = (UsersSecurity) authentication.getPrincipal();
        return usersSecurity.getId();
    }

    public Users get() {
        return usersRepository.findById(getId())
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, "User does not exist or is not authenticated"));
    }
}
