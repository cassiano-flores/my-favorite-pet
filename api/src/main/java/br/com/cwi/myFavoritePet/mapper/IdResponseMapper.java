package br.com.cwi.myFavoritePet.mapper;

import br.com.cwi.myFavoritePet.controller.response.IdResponse;

public class IdResponseMapper {

    public static IdResponse toResponse(Long id) {

        return IdResponse.builder()
                .id(id)
                .build();
    }
}
