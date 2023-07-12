package com.sparta.gunwooklv4.service;

import com.sparta.gunwooklv4.dto.LoginRequestDto;
import com.sparta.gunwooklv4.dto.SignupRequestDto;
import com.sparta.gunwooklv4.dto.StatusResult;
import com.sparta.gunwooklv4.entity.User;
import com.sparta.gunwooklv4.entity.UserRoleEnum;
import com.sparta.gunwooklv4.jwt.JwtUtil;
import com.sparta.gunwooklv4.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final String ADMIN_TOKEN = "GUNWOOK"; // 관리자 토큰


    // 회원가입 API
    public StatusResult signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        // 회원 중복 확인
        Optional<User> check = userRepository.findByUsername(username);
        if(check.isPresent()){
            return new StatusResult("이미 가입된 아이디 입니다.", 400);
        }

        UserRoleEnum role = UserRoleEnum.USER;
        // 관리자 체크 (admin=true 일시 수행)
        if(signupRequestDto.isAdmin()){
            if(!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)){
                return new StatusResult("관리자 번호가 틀렸습니다.", 400);
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록 (admin=false 일시 수행)
        User user = new User(username, password, role);
        userRepository.save(user);
        return new StatusResult("회원가입에 성공하였습니다.", 200);
    }


    // 로그인 API
    @Transactional(readOnly = true)
    public StatusResult login(LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        // 아이디 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록되지 않은 아이디 입니다.")
        );

        // 비밀번호 확인
        if(!passwordEncoder.matches(password, user.getPassword())){
            return new StatusResult("비밀번호가 일치하지 않습니다.", 400);
        }

        // 헤더에 Key값, Token값 추가
        httpServletResponse.addHeader(JwtUtil.AUTHORIZATION_HEADER,
                                        JwtUtil.createToken(user.getUsername(), user.getRole()));

        return new StatusResult("로그인에 성공하였습니다.", 200);
    }

}
