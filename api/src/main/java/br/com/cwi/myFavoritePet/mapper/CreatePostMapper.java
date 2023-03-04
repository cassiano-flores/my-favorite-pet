package br.com.cwi.myFavoritePet.mapper;

import br.com.cwi.myFavoritePet.controller.request.CreatePostRequest;
import br.com.cwi.myFavoritePet.domain.Post;

public class CreatePostMapper {

    public static Post toEntity(CreatePostRequest request) {
        Post entity = new Post();

        entity.setDescription(request.getDescription());
        entity.setPostImage(request.getPostImage());

        return entity;
    }
}
