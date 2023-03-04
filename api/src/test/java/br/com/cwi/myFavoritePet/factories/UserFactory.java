package br.com.cwi.myFavoritePet.factories;

import br.com.cwi.myFavoritePet.security.domain.Users;

import java.time.LocalDate;

public class UserFactory {

    public static Users get() {
        Users user = new Users();

        user.setId(SimpleFactory.getRandomLong());
        user.setName("Test User");
        user.setEmail("test@cwi.com.br");
        user.setBirthDate(LocalDate.of(2023, 2, 28));
        user.setActive(true);

        return user;
    }
}
