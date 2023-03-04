package br.com.cwi.myFavoritePet.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CommentPostRequest {

    @NotBlank
    private String comment;
}
