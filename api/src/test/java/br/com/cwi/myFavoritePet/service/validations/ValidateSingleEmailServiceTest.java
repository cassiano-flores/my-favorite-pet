package br.com.cwi.myFavoritePet.service.validations;

import br.com.cwi.myFavoritePet.security.repository.UsersRepository;
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
import static org.springframework.http.HttpStatus.CONFLICT;

@ExtendWith(MockitoExtension.class)
public class ValidateSingleEmailServiceTest {

    @InjectMocks
    private ValidateSingleEmailService tested;

    @Mock
    private UsersRepository usersRepository;

    @Test
    @DisplayName("Deve lançar exceção quando email já existir")
    void deveLancarExcecaoQuandoEmailJaExistir() {

        String email = "teste@teste.com";

        doThrow(new ResponseStatusException(CONFLICT)).when(usersRepository).existsByEmail(email);

        assertThrows(ResponseStatusException.class, () -> tested.validate(email));

        verify(usersRepository).existsByEmail(email);
    }
}
