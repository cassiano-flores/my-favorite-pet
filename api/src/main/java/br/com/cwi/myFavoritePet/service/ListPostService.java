package br.com.cwi.myFavoritePet.service;

import br.com.cwi.myFavoritePet.controller.response.ListPostResponse;
import br.com.cwi.myFavoritePet.mapper.ListPostMapper;
import br.com.cwi.myFavoritePet.repository.PostRepository;
import br.com.cwi.myFavoritePet.security.domain.Users;
import br.com.cwi.myFavoritePet.security.service.AuthenticatedUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListPostService {

    @Autowired
    private AuthenticatedUsersService authenticatedUsersService;

    @Autowired
    private PostRepository postRepository;

    public Page<ListPostResponse> list(Pageable pageable) {

        Users user = authenticatedUsersService.get();

        List<Long> friendIds = user.getFriendships()
                .stream()
                .map(Users::getId)
                .collect(Collectors.toList());

        friendIds.add(user.getId());

       return postRepository.findAllByUsersIdOrderByPostDateDesc(friendIds, pageable)
               .map(ListPostMapper::toResponse);
    }
}
