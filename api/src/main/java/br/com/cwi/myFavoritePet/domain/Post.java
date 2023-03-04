package br.com.cwi.myFavoritePet.domain;

import br.com.cwi.myFavoritePet.security.domain.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users usersId;

    private LocalDate postDate;

    @Enumerated(STRING)
    private Privacy privacy;

    private String description;
    private String postImage;

    @JsonIgnore
    @OneToMany(mappedBy = "postId")
    private List<PostLikes> likes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "postId")
    private List<PostComments> comments = new ArrayList<>();
}
