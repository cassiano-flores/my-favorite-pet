package br.com.cwi.myFavoritePet.repository;

import br.com.cwi.myFavoritePet.domain.PostComments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentsRepository extends JpaRepository<PostComments, Long> {
}
