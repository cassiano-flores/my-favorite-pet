package br.com.cwi.myFavoritePet.service.validations;

import br.com.cwi.myFavoritePet.security.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.CONFLICT;

@Service
public class ValidateSingleEmailService {

    @Autowired
    private UsersRepository usersRepository;

    public void validate(String email) {

        if (usersRepository.existsByEmail(email)) {
            throw new ResponseStatusException(CONFLICT, "The email already exists in the system");
        }
    }
}
