package br.com.cwi.myFavoritePet.factories;

import br.com.cwi.myFavoritePet.domain.Post;
import br.com.cwi.myFavoritePet.domain.Privacy;

import java.time.LocalDate;

public class PostFactory {

    public static Post get() {
        Post post = new Post();

        post.setId(SimpleFactory.getRandomLong());
        post.setPrivacy(Privacy.PUBLIC);
        post.setDescription("test");
        post.setPostDate(LocalDate.of(2023, 2, 28));

        return post;
    }
}
