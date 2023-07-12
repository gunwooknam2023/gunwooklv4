package com.sparta.gunwooklv4.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {
    private String username;
    private String title;
    private String contents;
}
