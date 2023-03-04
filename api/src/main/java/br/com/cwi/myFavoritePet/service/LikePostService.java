package br.com.cwi.myFavoritePet.service;

import br.com.cwi.myFavoritePet.controller.response.IdResponse;
import br.com.cwi.myFavoritePet.domain.Post;
import br.com.cwi.myFavoritePet.domain.PostLikes;
import br.com.cwi.myFavoritePet.mapper.IdResponseMapper;
import br.com.cwi.myFavoritePet.repository.PostLikesRepository;
import br.com.cwi.myFavoritePet.repository.PostRepository;
import br.com.cwi.myFavoritePet.security.domain.Users;
import br.com.cwi.myFavoritePet.security.service.AuthenticatedUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LikePostService {

    @Autowired
    private AuthenticatedUsersService authenticatedUsersService;

    @Autowired
    private SearchPostService searchPostService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostLikesRepository postLikesRepository;

    @Transactional
    public IdResponse likePost(Long postId) {

        Users user = authenticatedUsersService.get();
        Post post = searchPostService.byId(postId);
        PostLikes like = postLikesRepository.findByPostIdAndUsersIdId(post, user.getId());

        PostLikes newLike = null;

        if (like == null) {

            newLike = new PostLikes();
            newLike.setPostId(post);
            newLike.setUsersId(user);

            postLikesRepository.save(newLike);
            post.getLikes().add(newLike);

        } else {
            post.getLikes().remove(like);
            postLikesRepository.delete(like);
        }

        postRepository.save(post);

        return IdResponseMapper.toResponse(like != null ? like.getId() : newLike.getId());
    }
}
