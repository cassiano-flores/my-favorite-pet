package br.com.cwi.myFavoritePet.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditProfileRequest {

    private String name;
    private String nickname;
    private String profileImage;
}
