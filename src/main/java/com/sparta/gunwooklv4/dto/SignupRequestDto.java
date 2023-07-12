package com.sparta.gunwooklv4.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequestDto {

    @NotBlank
    @Size(min = 4, max = 10, message = "아이디는 4글자 이상, 10글자 이하만 가입이 가능합니다.")
    @Pattern(regexp = "^[a-z0-9]*$", message = "아이디는 알파벳 소문자, 숫자로만 가입이 가능합니다.")
    private String username;

    @NotBlank
    @Size(min = 8, max = 15, message = "비밀번호는 8글자 이상, 15글자 이하만 사용이 가능합니다.")
    @Pattern(regexp = "^[a-z0-9!@#$%^&*()_+]*$", message = "비밀번호는 알파벳 소문자, 숫자, 특수문자로만 가입이 가능합니다.")
    private String password;

    private boolean admin = false; // 관리자로 회원가입시 true로 변환. 디폴트는 false로 설정
    private String adminToken = "";

}
