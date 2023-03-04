package br.com.cwi.myFavoritePet.service;

import br.com.cwi.myFavoritePet.controller.response.ListPostResponse;
import br.com.cwi.myFavoritePet.domain.Privacy;
import br.com.cwi.myFavoritePet.mapper.ListPostMapper;
import br.com.cwi.myFavoritePet.repository.PostRepository;
import br.com.cwi.myFavoritePet.security.domain.Users;
import br.com.cwi.myFavoritePet.security.service.AuthenticatedUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListPostAnotherUserService {

    @Autowired
    private AuthenticatedUsersService authenticatedUsersService;

    @Autowired
    private SearchUsersService searchUsersService;

    @Autowired
    private PostRepository postRepository;

    public Page<ListPostResponse> list(Long userId, Pageable pageable) {

        Users user = authenticatedUsersService.get();
        Users userToListPosts = searchUsersService.byId(userId);

        List<Long> listId = new ArrayList<>();
        listId.add(userToListPosts.getId());

        if (userToListPosts.hasFriendshipWith(user)) {

            return postRepository.findAllByUsersIdOrderByPostDateDesc(listId, pageable)
                    .map(ListPostMapper::toResponse);
        } else {

            return postRepository.findAllByUsersIdAndPrivacyOrderByPostDateDesc
                     (userToListPosts, Privacy.PUBLIC, pageable)
                    .map(ListPostMapper::toResponse);
        }
    }
}
