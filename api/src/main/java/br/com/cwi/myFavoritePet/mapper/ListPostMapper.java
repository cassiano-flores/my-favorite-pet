package br.com.cwi.myFavoritePet.mapper;

import br.com.cwi.myFavoritePet.controller.response.ListPostResponse;
import br.com.cwi.myFavoritePet.domain.Post;

public class ListPostMapper {

    public static ListPostResponse toResponse(Post post) {

        return ListPostResponse.builder()
                .id(post.getId())
                .usersId(post.getUsersId())
                .postDate(post.getPostDate())
                .privacy(post.getPrivacy())
                .description(post.getDescription())
                .postImage(post.getPostImage())
                .likes(post.getLikes())
                .comments(post.getComments())
                .build();
    }
}
