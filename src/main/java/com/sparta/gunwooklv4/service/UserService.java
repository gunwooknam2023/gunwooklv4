package com.sparta.gunwooklv4.service;

import com.sparta.gunwooklv4.dto.LoginRequestDto;
import com.sparta.gunwooklv4.dto.SignupRequestDto;
import com.sparta.gunwooklv4.entity.User;
import com.sparta.gunwooklv4.entity.UserRoleEnum;
import com.sparta.gunwooklv4.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.sparta.gunwooklv4.repository.CommentRepository;
import com.sparta.gunwooklv4.repository.PostRepository;
import com.sparta.gunwooklv4.repository.UserRepository;

import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PasswordEncoder passwordEncoder;
    private final String ADMIN_TOKEN = "GUNWOOK"; // 관리자 토큰


    // 회원가입 API
    public void signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        // 회원 중복 확인
        Optional<User> check = userRepository.findByUsername(username);
        if(check.isPresent()){
            throw new IllegalArgumentException("이미 사용중인 아이디 입니다.");
        }


        UserRoleEnum role = UserRoleEnum.USER;

        // 관리자 체크 (admin=true 일시 수행)
        if(signupRequestDto.isAdmin()){
            if(!ADMIN_TOKEN.equals(signupRequestDto.getAdminToken())){
                throw new IllegalArgumentException("관리자 코드가 일치하지 않습니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록 (admin=false 일시 수행)
        User user = new User(username, password, role);
        userRepository.save(user);
    }


    // 로그인 API
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        // 아이디 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 아이디 입니다.")
        );

        // 비밀번호 확인
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 헤더에 Key값, Token값 추가
        httpServletResponse.addHeader(JwtUtil.AUTHORIZATION_HEADER,
                                        JwtUtil.createToken(user.getUsername(), user.getRole()));

    }

}
