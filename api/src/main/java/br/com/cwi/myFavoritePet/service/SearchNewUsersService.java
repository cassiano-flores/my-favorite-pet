package br.com.cwi.myFavoritePet.service;

import br.com.cwi.myFavoritePet.security.domain.Users;
import br.com.cwi.myFavoritePet.security.repository.UsersRepository;
import br.com.cwi.myFavoritePet.security.service.AuthenticatedUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchNewUsersService {

    @Autowired
    private AuthenticatedUsersService authenticatedUsersService;

    @Autowired
    private UsersRepository usersRepository;

    public List<Users> search(String text) {

        Users user = authenticatedUsersService.get();

        return usersRepository.findByNameOrEmailContainingIgnoreCaseAndIdNot
                (text, text, user.getId());
    }
}
