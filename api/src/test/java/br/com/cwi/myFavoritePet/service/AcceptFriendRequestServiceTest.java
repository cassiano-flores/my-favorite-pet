package br.com.cwi.myFavoritePet.service;

import br.com.cwi.myFavoritePet.factories.UserFactory;
import br.com.cwi.myFavoritePet.repository.FriendRequestRepository;
import br.com.cwi.myFavoritePet.security.domain.Users;
import br.com.cwi.myFavoritePet.security.repository.UsersRepository;
import br.com.cwi.myFavoritePet.security.service.AuthenticatedUsersService;
import br.com.cwi.myFavoritePet.service.validations.ValidateAcceptFriendRequestService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ExtendWith(MockitoExtension.class)
public class AcceptFriendRequestServiceTest {

    @InjectMocks
    private AcceptFriendRequestService tested;

    @Mock
    private ValidateAcceptFriendRequestService validateAcceptFriendRequestService;

    @Mock
    private AuthenticatedUsersService authenticatedUsersService;

    @Mock
    private SearchUsersService searchUsersService;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private FriendRequestRepository friendRequestRepository;

    @Test
    @DisplayName("Deve aceitar solicitação, insere amigo em friendship e exclui friendRequest")
    void deveAceitarSolicitacaoAdicionandoEmAmigos() {

        Users user = UserFactory.get();
        Users userToAccept = UserFactory.get();
        Long userToAcceptId = userToAccept.getId();

        when(authenticatedUsersService.get()).thenReturn(user);
        when(searchUsersService.byId(userToAcceptId)).thenReturn(userToAccept);

        tested.accept(userToAcceptId);

        verify(validateAcceptFriendRequestService).validate(userToAcceptId);
        verify(authenticatedUsersService).get();
        verify(searchUsersService).byId(userToAcceptId);
        verify(friendRequestRepository).deleteBySenderId(userToAccept);
        verify(usersRepository).save(user);
        verify(usersRepository).save(userToAccept);
    }

    @Test
    @DisplayName("Não deve aceitar solicitação quando Id da solicitação não existir")
    void naoDeveAceitarQuandoUserIdDaSolicitacaoNaoExistir() {

        Users userToAccept = UserFactory.get();
        Long userToAcceptId = userToAccept.getId();

        doThrow(new ResponseStatusException(NOT_FOUND)).when(validateAcceptFriendRequestService).validate(userToAcceptId);

        assertThrows(ResponseStatusException.class, () -> tested.accept(userToAcceptId));

        verify(validateAcceptFriendRequestService).validate(userToAcceptId);
    }

    @Test
    @DisplayName("Não deve aceitar solicitação quando User não estiver autenticado")
    void naoDeveAceitarQuandoUserNaoEstiverAutenticado() {

        Users userToAccept = UserFactory.get();
        Long userToAcceptId = userToAccept.getId();

        doThrow(new ResponseStatusException(INTERNAL_SERVER_ERROR)).when(authenticatedUsersService).get();

        assertThrows(ResponseStatusException.class, () -> tested.accept(userToAcceptId));

        verify(validateAcceptFriendRequestService).validate(userToAcceptId);
        verify(authenticatedUsersService).get();
    }

    @Test
    @DisplayName("Não deve aceitar solicitação quando o User para aceitar não existir")
    void naoDeveAceitarQuandoUserParaAceitarNaoExistir() {

        Users userToAccept = UserFactory.get();
        Long userToAcceptId = userToAccept.getId();

        doThrow(new ResponseStatusException(NOT_FOUND)).when(searchUsersService).byId(userToAcceptId);

        assertThrows(ResponseStatusException.class, () -> tested.accept(userToAcceptId));

        verify(validateAcceptFriendRequestService).validate(userToAcceptId);
        verify(authenticatedUsersService).get();
        verify(searchUsersService).byId(userToAcceptId);
    }
}
