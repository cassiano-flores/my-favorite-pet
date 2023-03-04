package br.com.cwi.myFavoritePet.mapper;

import br.com.cwi.myFavoritePet.controller.request.RegisterUsersRequest;
import br.com.cwi.myFavoritePet.security.domain.Users;

public class RegisterUsersMapper {

    public static Users toEntity(RegisterUsersRequest request) {

        return Users.builder()
                .name(request.getName())
                .email(request.getEmail())
                .nickname(request.getNickname())
                .birthDate(request.getBirthDate())
                .password(request.getPassword())
                .profileImage(request.getProfileImage())
                .build();
    }
}
