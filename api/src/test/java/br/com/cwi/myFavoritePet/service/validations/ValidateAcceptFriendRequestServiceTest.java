package br.com.cwi.myFavoritePet.service.validations;

import br.com.cwi.myFavoritePet.factories.UserFactory;
import br.com.cwi.myFavoritePet.security.domain.Users;
import br.com.cwi.myFavoritePet.security.service.AuthenticatedUsersService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ExtendWith(MockitoExtension.class)
public class ValidateAcceptFriendRequestServiceTest {

    @InjectMocks
    private ValidateAcceptFriendRequestService tested;

    @Mock
    private AuthenticatedUsersService authenticatedUsersService;

    @Test
    @DisplayName("Deve lançar exceção quando User não estiver autenticado")
    void naoDeveAceitarQuandoUserNaoEstiverAutenticado() {

        Users userToAccept = UserFactory.get();
        Long userToAcceptId = userToAccept.getId();

        doThrow(new ResponseStatusException(INTERNAL_SERVER_ERROR)).when(authenticatedUsersService).get();

        assertThrows(ResponseStatusException.class, () -> tested.validate(userToAcceptId));

        verify(authenticatedUsersService).get();
    }
}
