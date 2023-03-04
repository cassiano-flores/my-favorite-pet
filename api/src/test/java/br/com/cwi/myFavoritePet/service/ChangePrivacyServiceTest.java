package br.com.cwi.myFavoritePet.service;

import br.com.cwi.myFavoritePet.domain.Post;
import br.com.cwi.myFavoritePet.factories.PostFactory;
import br.com.cwi.myFavoritePet.factories.SimpleFactory;
import br.com.cwi.myFavoritePet.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ExtendWith(MockitoExtension.class)
public class ChangePrivacyServiceTest {

    @InjectMocks
    private ChangePrivacyService tested;

    @Mock
    private SearchPostService searchPostService;

    @Mock
    private PostRepository postRepository;

    @Test
    @DisplayName("Deve alterar a privacy com sucesso")
    void deveAlterarPrivacyComSucesso() {

        Long randomId = SimpleFactory.getRandomLong();
        Post post = PostFactory.get();

        when(searchPostService.byId(randomId)).thenReturn(post);

        tested.changePrivacy(randomId);

        verify(searchPostService).byId(randomId);
        verify(postRepository).save(post);
    }

    @Test
    @DisplayName("Não deve alterar privacy quando não encontrar o id do post")
    void naoDeveAlterarPrivacyQuandoNaoEncontrarIdPost() {

        Long randomId = SimpleFactory.getRandomLong();

        doThrow(new ResponseStatusException(NOT_FOUND)).when(searchPostService).byId(randomId);

        assertThrows(ResponseStatusException.class, () -> tested.changePrivacy(randomId));

        verify(searchPostService).byId(randomId);
    }
}
