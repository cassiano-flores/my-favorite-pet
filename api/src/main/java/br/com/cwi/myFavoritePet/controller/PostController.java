package br.com.cwi.myFavoritePet.controller;

import br.com.cwi.myFavoritePet.controller.request.CommentPostRequest;
import br.com.cwi.myFavoritePet.controller.request.CreatePostRequest;
import br.com.cwi.myFavoritePet.controller.response.IdResponse;
import br.com.cwi.myFavoritePet.controller.response.ListPostResponse;
import br.com.cwi.myFavoritePet.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private CreatePostService createPostService;

    @Autowired
    private ListPostService listPostService;

    @Autowired
    private ListPostAnotherUserService listPostAnotherUserService;

    @Autowired
    private LikePostService likePostService;

    @Autowired
    private CommentPostService commentPostService;

    @Autowired
    private ChangePrivacyService changePrivacyService;

    @PostMapping()
    @ResponseStatus(CREATED)
    public IdResponse create(@Valid @RequestBody CreatePostRequest request) {
        return createPostService.create(request);
    }

    @GetMapping()
    @ResponseStatus(OK)
    public Page<ListPostResponse> list(Pageable pageable) {
        return listPostService.list(pageable);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(OK)
    public Page<ListPostResponse> listAnotherUser(@PathVariable Long userId, Pageable pageable) {
        return listPostAnotherUserService.list(userId, pageable);
    }

    @PostMapping("/like/{postId}")
    @ResponseStatus(CREATED)
    public IdResponse likePost(@PathVariable Long postId) {
        return likePostService.likePost(postId);
    }

    @PostMapping("/comment/{postId}")
    @ResponseStatus(CREATED)
    public IdResponse commentPost(@PathVariable Long postId, @Valid @RequestBody CommentPostRequest request) {
        return commentPostService.commentPost(postId, request);
    }

    @PutMapping("/privacy/{postId}")
    @ResponseStatus(OK)
    public void changePrivacy(@PathVariable Long postId) {
        changePrivacyService.changePrivacy(postId);
    }
}
