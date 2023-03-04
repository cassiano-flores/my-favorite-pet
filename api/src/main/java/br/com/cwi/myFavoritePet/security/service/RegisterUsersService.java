package br.com.cwi.myFavoritePet.security.service;

import br.com.cwi.myFavoritePet.controller.request.RegisterUsersRequest;
import br.com.cwi.myFavoritePet.controller.response.IdResponse;
import br.com.cwi.myFavoritePet.security.domain.Users;
import br.com.cwi.myFavoritePet.security.repository.UsersRepository;
import br.com.cwi.myFavoritePet.service.validations.ValidateSingleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.myFavoritePet.mapper.IdResponseMapper.toResponse;
import static br.com.cwi.myFavoritePet.mapper.RegisterUsersMapper.toEntity;

@Service
public class RegisterUsersService {

    @Autowired
    private ValidateSingleEmailService validateSingleEmailService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public IdResponse register(RegisterUsersRequest request) {

        validateSingleEmailService.validate(request.getEmail());

        Users user = toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setActive(true);

//        request.getPermissions()
//                .forEach(permission -> user.adicionarPermissao(Permission.builder().name(permission).build()));

        usersRepository.save(user);

        return toResponse(user.getId());
    }
}
