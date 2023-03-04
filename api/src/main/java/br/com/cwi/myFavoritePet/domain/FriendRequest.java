package br.com.cwi.myFavoritePet.domain;

import br.com.cwi.myFavoritePet.security.domain.Users;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity
public class FriendRequest {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users usersId;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Users senderId;
}
