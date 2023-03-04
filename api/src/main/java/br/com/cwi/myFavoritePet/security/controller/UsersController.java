package br.com.cwi.myFavoritePet.security.controller;

import br.com.cwi.myFavoritePet.controller.request.RegisterUsersRequest;
import br.com.cwi.myFavoritePet.controller.response.IdResponse;
import br.com.cwi.myFavoritePet.controller.request.EditProfileRequest;
import br.com.cwi.myFavoritePet.security.controller.response.UsersResponse;
import br.com.cwi.myFavoritePet.security.domain.Users;
import br.com.cwi.myFavoritePet.service.EditProfileService;
import br.com.cwi.myFavoritePet.security.service.RegisterUsersService;
import br.com.cwi.myFavoritePet.security.service.FindUsersService;
import br.com.cwi.myFavoritePet.service.UsersProfileService;
import br.com.cwi.myFavoritePet.service.SearchNewUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private FindUsersService findUsersService;

    @Autowired
    private RegisterUsersService registerUsersService;

    @Autowired
    private SearchNewUsersService searchNewUsersService;

    @Autowired
    private UsersProfileService usersProfileService;

    @Autowired
    private EditProfileService editProfileService;

    @GetMapping("/me")
    @ResponseStatus(OK)
    public UsersResponse search() {
        return findUsersService.search();
    }

    @PostMapping("/register")
    @ResponseStatus(CREATED)
    public IdResponse register(@Valid @RequestBody RegisterUsersRequest request) {
        return registerUsersService.register(request);
    }

    @PostMapping("/login")
    @ResponseStatus(OK)
    public UsersResponse login() {
        return findUsersService.search();
    }

    @GetMapping()
    @ResponseStatus(OK)
    public List<Users> searchUsers(@RequestParam String text) {
        return searchNewUsersService.search(text);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(OK)
    public UsersResponse userProfile(@PathVariable Long userId) {
        return usersProfileService.userProfile(userId);
    }

    @PutMapping("/me/profile")
    @ResponseStatus(OK)
    public IdResponse editProfile(@RequestBody EditProfileRequest request) {
        return editProfileService.editProfile(request);
    }
}
