package br.com.cwi.myFavoritePet.service;

import br.com.cwi.myFavoritePet.controller.request.CommentPostRequest;
import br.com.cwi.myFavoritePet.controller.response.IdResponse;
import br.com.cwi.myFavoritePet.domain.Post;
import br.com.cwi.myFavoritePet.domain.PostComments;
import br.com.cwi.myFavoritePet.mapper.IdResponseMapper;
import br.com.cwi.myFavoritePet.repository.PostCommentsRepository;
import br.com.cwi.myFavoritePet.repository.PostRepository;
import br.com.cwi.myFavoritePet.security.domain.Users;
import br.com.cwi.myFavoritePet.security.service.AuthenticatedUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentPostService {

    @Autowired
    private AuthenticatedUsersService authenticatedUsersService;

    @Autowired
    private SearchPostService searchPostService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostCommentsRepository postCommentsRepository;

    @Transactional
    public IdResponse commentPost(Long postId, CommentPostRequest request) {

        Users user = authenticatedUsersService.get();
        Post post = searchPostService.byId(postId);

        PostComments postComment = new PostComments();
        postComment.setPostId(post);
        postComment.setUsersId(user);
        postComment.setUserComment(request.getComment());

        postCommentsRepository.save(postComment);

        post.getComments().add(postComment);

        postRepository.save(post);

        return IdResponseMapper.toResponse(postComment.getId());
    }
}
