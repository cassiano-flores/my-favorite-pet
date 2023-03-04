package br.com.cwi.myFavoritePet.service;

import br.com.cwi.myFavoritePet.security.controller.response.UsersResponse;
import br.com.cwi.myFavoritePet.security.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.cwi.myFavoritePet.security.mapper.UsersMapper.toResponse;

@Service
public class UsersProfileService {

    @Autowired
    private SearchUsersService searchUsersService;

    public UsersResponse userProfile(Long userId) {

        Users user = searchUsersService.byId(userId);

        return toResponse(user);
    }
}
