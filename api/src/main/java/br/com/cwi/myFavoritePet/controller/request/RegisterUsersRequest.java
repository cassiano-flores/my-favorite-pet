package br.com.cwi.myFavoritePet.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class RegisterUsersRequest {

    @NotBlank
    private String name;

    @NotBlank @Email
    private String email;

    private String nickname;

    @NotNull
    private LocalDate birthDate;

    @NotBlank
    private String password;

    private String profileImage;

//    @NotNull @NotEmpty
//    private List<String> permissions;
}
