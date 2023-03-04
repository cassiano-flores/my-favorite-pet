package br.com.cwi.myFavoritePet.security.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter
public class UsersRequest {

    @NotBlank
    private String name;

    @NotNull @Email
    private String email;

    @NotBlank
    private String password;

    @NotNull @NotEmpty
    private List<String> permissions;

}
