package br.com.cwi.myFavoritePet.service;

import br.com.cwi.myFavoritePet.domain.Post;
import br.com.cwi.myFavoritePet.domain.Privacy;
import br.com.cwi.myFavoritePet.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ChangePrivacyService {

    @Autowired
    private SearchPostService searchPostService;

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public void changePrivacy(Long postId) {

        Post post = searchPostService.byId(postId);

        post.setPrivacy(post.getPrivacy() == Privacy.PRIVATE ? Privacy.PUBLIC : Privacy.PRIVATE);

        postRepository.save(post);
    }
}
