package br.com.cwi.myFavoritePet.security.mapper;

import br.com.cwi.myFavoritePet.security.controller.request.UsersRequest;
import br.com.cwi.myFavoritePet.security.controller.response.UsersResponse;
import br.com.cwi.myFavoritePet.security.domain.Permission;
import br.com.cwi.myFavoritePet.security.domain.Users;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class UsersMapper {

    public static Users toEntity(UsersRequest request) {
        Users entity = new Users();
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        return entity;
    }

    public static UsersResponse toResponse(Users entity) {
        return UsersResponse.builder()
                .name(entity.getName())
                .email(entity.getEmail())
                .nickname(entity.getNickname())
                .birthDate(entity.getBirthDate())
                .profileImage(entity.getProfileImage())
                .friendships(entity.getFriendships())
                .friendRequests(entity.getFriendRequests())
                .posts(entity.getPosts())
                .permissions(buildPermissoesResponse(entity.getPermissions()))
                .build();
    }

    private static List<String> buildPermissoesResponse(List<Permission> permissoes) {
        return permissoes.stream()
                .map(Permission::getName)
                .collect(toList());
    }
}
