package br.com.cwi.myFavoritePet.controller.request;

import br.com.cwi.myFavoritePet.domain.Privacy;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreatePostRequest {

    private Privacy privacy;
    private String postImage;

    @NotBlank
    private String description;
}
