package br.com.cwi.myFavoritePet.controller.response;

import br.com.cwi.myFavoritePet.domain.Post;
import br.com.cwi.myFavoritePet.domain.PostComments;
import br.com.cwi.myFavoritePet.domain.PostLikes;
import br.com.cwi.myFavoritePet.domain.Privacy;
import br.com.cwi.myFavoritePet.security.domain.Users;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ListPostResponse {

    private Long id;
    private Users usersId;
    private LocalDate postDate;
    private Privacy privacy;
    private String description;
    private String postImage;
    private List<PostLikes> likes;
    private List<PostComments> comments;

    public ListPostResponse(Post post) {

    }
}
