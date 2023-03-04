package br.com.cwi.myFavoritePet.repository;

import br.com.cwi.myFavoritePet.domain.Post;
import br.com.cwi.myFavoritePet.domain.PostLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikesRepository extends JpaRepository<PostLikes, Long> {

    PostLikes findByPostIdAndUsersIdId(Post postId, Long usersId_id);
}
