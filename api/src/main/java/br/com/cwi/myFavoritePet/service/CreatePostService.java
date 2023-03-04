package br.com.cwi.myFavoritePet.service;

import br.com.cwi.myFavoritePet.controller.request.CreatePostRequest;
import br.com.cwi.myFavoritePet.controller.response.IdResponse;
import br.com.cwi.myFavoritePet.domain.Post;
import br.com.cwi.myFavoritePet.domain.Privacy;
import br.com.cwi.myFavoritePet.mapper.CreatePostMapper;
import br.com.cwi.myFavoritePet.repository.PostRepository;
import br.com.cwi.myFavoritePet.security.domain.Users;
import br.com.cwi.myFavoritePet.security.service.AuthenticatedUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

import static br.com.cwi.myFavoritePet.mapper.IdResponseMapper.toResponse;

@Service
public class CreatePostService {

    @Autowired
    private AuthenticatedUsersService authenticatedUsersService;

    @Autowired
    private NowService nowService;

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public IdResponse create(CreatePostRequest request) {

        Users user = authenticatedUsersService.get();

        Post post = CreatePostMapper.toEntity(request);
        post.setUsersId(user);
        post.setPostDate(nowService.today());
        post.setPrivacy(Objects.requireNonNullElse(request.getPrivacy(), Privacy.PUBLIC));

        postRepository.save(post);

        return toResponse(post.getId());
    }
}
