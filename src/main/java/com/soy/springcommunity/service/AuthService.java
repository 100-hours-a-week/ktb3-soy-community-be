package com.soy.springcommunity.service;

import com.soy.springcommunity.dto.UsersSignInRequest;
import com.soy.springcommunity.dto.UsersSignInResponse;
import com.soy.springcommunity.entity.Users;
import com.soy.springcommunity.exception.UsersException;
import com.soy.springcommunity.repository.users.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private UsersRepository usersRepository;

    @Autowired
    public AuthService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    public UsersSignInResponse signIn(UsersSignInRequest usersSignInRequest) {

        String email = usersSignInRequest.getEmail();
        String password = usersSignInRequest.getPassword();

        Users users = usersRepository.findByEmailAndIsDeletedFalse(email)
                .orElseThrow(() -> new UsersException.UsersNotFoundException("존재하지 않는 사용자입니다."));

        return new UsersSignInResponse(
                users.getId(),
                users.getNickname(),
                users.getFilesUserProfileImgUrl().getImgUrl(),
                users.getRole().name()
        );
    }




}
