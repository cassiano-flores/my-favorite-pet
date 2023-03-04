package br.com.cwi.myFavoritePet.repository;

import br.com.cwi.myFavoritePet.domain.Post;
import br.com.cwi.myFavoritePet.domain.Privacy;
import br.com.cwi.myFavoritePet.security.domain.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.usersId.id IN (:userIds) ORDER BY p.postDate DESC")
    Page<Post> findAllByUsersIdOrderByPostDateDesc(@Param("userIds") List<Long> userIds, Pageable pageable);


    Page<Post> findAllByUsersIdAndPrivacyOrderByPostDateDesc(Users usersId, Privacy privacy, Pageable pageable);
}
