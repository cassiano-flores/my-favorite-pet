package br.com.cwi.myFavoritePet.service;

import br.com.cwi.myFavoritePet.controller.request.EditProfileRequest;
import br.com.cwi.myFavoritePet.controller.response.IdResponse;
import br.com.cwi.myFavoritePet.mapper.IdResponseMapper;
import br.com.cwi.myFavoritePet.security.domain.Users;
import br.com.cwi.myFavoritePet.security.repository.UsersRepository;
import br.com.cwi.myFavoritePet.security.service.AuthenticatedUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class EditProfileService {

    @Autowired
    private AuthenticatedUsersService authenticatedUsersService;

    @Autowired
    private UsersRepository usersRepository;

    @Transactional
    public IdResponse editProfile(EditProfileRequest request) {

        Users user = authenticatedUsersService.get();

        user.setName(Objects.requireNonNullElse(request.getName(), user.getName()));
        user.setNickname(request.getNickname() != null ? request.getNickname() : user.getNickname());
        user.setProfileImage(request.getProfileImage() != null ? request.getProfileImage() : user.getProfileImage());

        usersRepository.save(user);

        return IdResponseMapper.toResponse(user.getId());
    }
}
