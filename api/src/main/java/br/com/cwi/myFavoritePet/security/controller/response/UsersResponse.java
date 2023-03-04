package br.com.cwi.myFavoritePet.security.controller.response;

import br.com.cwi.myFavoritePet.domain.FriendRequest;
import br.com.cwi.myFavoritePet.domain.Post;
import br.com.cwi.myFavoritePet.security.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsersResponse {

    private String name;
    private String email;
    private String nickname;
    private LocalDate birthDate;
    private String profileImage;

    private List<Users> friendships;
    private List<FriendRequest> friendRequests;
    private List<Post> posts;

    private List<String> permissions;
}
