package com.sparta.gunwooklv4.controller;

import com.sparta.gunwooklv4.dto.LoginRequestDto;
import com.sparta.gunwooklv4.dto.SignupRequestDto;
import com.sparta.gunwooklv4.dto.StatusResult;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sparta.gunwooklv4.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;


    // 회원가입 API
    @PostMapping("/signup")
    public ResponseEntity<StatusResult> signup(@Valid @RequestBody SignupRequestDto signupRequestDto){
        userService.signup(signupRequestDto);
        return ResponseEntity.ok(new StatusResult("회원가입이 완료되었습니다.", HttpStatus.OK.value()));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<StatusResult> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse){
        userService.login(loginRequestDto, httpServletResponse);
        return ResponseEntity.ok(new StatusResult("로그인이 완료되었습니다.", HttpStatus.OK.value()));
    }

}
