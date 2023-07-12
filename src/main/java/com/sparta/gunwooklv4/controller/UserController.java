package com.sparta.gunwooklv4.controller;

import com.sparta.gunwooklv4.dto.LoginRequestDto;
import com.sparta.gunwooklv4.dto.SignupRequestDto;
import com.sparta.gunwooklv4.dto.StatusResult;
import com.sparta.gunwooklv4.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;


    // 회원가입 API
    @PostMapping("/signup")
    public StatusResult signup(@Valid @RequestBody SignupRequestDto signupRequestDto){
        return userService.signup(signupRequestDto);
    }

    // 로그인
    @PostMapping("/login")
    public StatusResult login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse){
        return userService.login(loginRequestDto,httpServletResponse);
    }

}
