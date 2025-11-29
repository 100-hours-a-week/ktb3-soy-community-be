package com.soy.springcommunity.controller;

import com.soy.springcommunity.dto.ApiCommonResponse;
import com.soy.springcommunity.dto.UsersApiResponse;
import com.soy.springcommunity.dto.UsersSignInRequest;
import com.soy.springcommunity.dto.UsersSignInResponse;
import com.soy.springcommunity.service.AuthService;
import com.soy.springcommunity.service.UsersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiCommonResponse<UsersSignInResponse>> logout(
            @RequestBody UsersSignInRequest req,
            HttpSession session) {
        UsersSignInResponse signInResponse = authService.signIn(req);
        session.setAttribute("user", req.getEmail());
        return UsersApiResponse.created(HttpStatus.CREATED,
                "로그인 성공",
                signInResponse);
    }

}
