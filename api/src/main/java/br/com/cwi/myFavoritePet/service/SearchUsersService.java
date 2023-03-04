package br.com.cwi.myFavoritePet.service;

import br.com.cwi.myFavoritePet.security.domain.Users;
import br.com.cwi.myFavoritePet.security.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class SearchUsersService {

    @Autowired
    private UsersRepository usersRepository;

    public Users byId(Long userId) {

        return usersRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "User not found"));
    }
}
