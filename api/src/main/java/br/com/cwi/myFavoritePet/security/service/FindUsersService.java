package br.com.cwi.myFavoritePet.security.service;

import br.com.cwi.myFavoritePet.security.controller.response.UsersResponse;
import br.com.cwi.myFavoritePet.security.domain.Users;
import br.com.cwi.myFavoritePet.security.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindUsersService {

    @Autowired
    private AuthenticatedUsersService authenticatedUsersService;

    public UsersResponse search() {
        Users authenticatedUser = authenticatedUsersService.get();
        return UsersMapper.toResponse(authenticatedUser);
    }
}
