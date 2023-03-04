package br.com.cwi.myFavoritePet.security.repository;

import br.com.cwi.myFavoritePet.security.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);

    boolean existsByEmail(String userEmail);

    List<Users> findByNameOrEmailContainingIgnoreCaseAndIdNot
            (String name, String email, Long id);

    @Query("SELECT u FROM Users u JOIN u.friendships f WHERE (u.name LIKE %:text% OR u.email LIKE %:text%) AND f.id = :userId")
    List<Users> findFriendsByUserAndNameOrEmailContainingIgnoreCase(String text, Long userId);
}
