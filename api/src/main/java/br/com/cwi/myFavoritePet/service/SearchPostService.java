package br.com.cwi.myFavoritePet.service;

import br.com.cwi.myFavoritePet.domain.Post;
import br.com.cwi.myFavoritePet.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class SearchPostService {

    @Autowired
    private PostRepository postRepository;

    public Post byId(Long postId) {

        return postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Post not found"));
    }
}
