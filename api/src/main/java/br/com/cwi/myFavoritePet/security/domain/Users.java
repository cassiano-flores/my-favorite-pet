package br.com.cwi.myFavoritePet.security.domain;

import br.com.cwi.myFavoritePet.domain.FriendRequest;
import br.com.cwi.myFavoritePet.domain.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String nickname;
    private LocalDate birthDate;
    private String password;
    private boolean active;
    private String profileImage;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Permission> permissions = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "friendship",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private List<Users> friendships = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usersId")
    private List<FriendRequest> friendRequests = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usersId")
    private List<Post> posts = new ArrayList<>();


    public void adicionarPermissao(Permission permission) {
        this.permissions.add(permission);
        permission.setUsers(this);
    }

    public boolean hasFriendshipWith(Users user) {
        return friendships.contains(user);
    }
}
