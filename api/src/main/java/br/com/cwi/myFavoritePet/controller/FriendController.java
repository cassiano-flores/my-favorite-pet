    package br.com.cwi.myFavoritePet.controller;

    import br.com.cwi.myFavoritePet.controller.response.IdResponse;
    import br.com.cwi.myFavoritePet.security.domain.Users;
    import br.com.cwi.myFavoritePet.service.AcceptFriendRequestService;
    import br.com.cwi.myFavoritePet.service.CreateFriendRequestService;
    import br.com.cwi.myFavoritePet.service.RemoveFriendService;
    import br.com.cwi.myFavoritePet.service.SearchFriendsService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    import static org.springframework.http.HttpStatus.CREATED;
    import static org.springframework.http.HttpStatus.OK;

    @RestController
    @RequestMapping("/friends")
    public class FriendController {

        @Autowired
        private AcceptFriendRequestService acceptFriendRequestService;

        @Autowired
        private CreateFriendRequestService createFriendRequestService;

        @Autowired
        private RemoveFriendService removeFriendService;

        @Autowired
        private SearchFriendsService searchFriendsService;

        @PutMapping("/accept/{userId}")
        @ResponseStatus(OK)
        public void acceptFriendRequest(@PathVariable Long userId) {
            acceptFriendRequestService.accept(userId);
        }

        @PostMapping("/send/{userId}")
        @ResponseStatus(CREATED)
        public IdResponse createFriendRequest(@PathVariable Long userId) {
            return createFriendRequestService.create(userId);
        }

        @DeleteMapping("/remove/{userId}")
        @ResponseStatus(OK)
        public void removeFriend(@PathVariable Long userId) {
            removeFriendService.remove(userId);
        }

        @GetMapping()
        @ResponseStatus(OK)
        public List<Users> searchFriends(@RequestParam String text) {
            return searchFriendsService.search(text);
        }
    }
