package br.com.cwi.myFavoritePet.repository;

import br.com.cwi.myFavoritePet.domain.FriendRequest;
import br.com.cwi.myFavoritePet.security.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    void deleteBySenderId(Users senderId);

    FriendRequest findBySenderIdIdAndUsersIdId(Long id, Long userId);
}
