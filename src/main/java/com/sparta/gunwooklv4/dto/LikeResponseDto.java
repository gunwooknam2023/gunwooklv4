package com.sparta.gunwooklv4.dto;

import com.sparta.gunwooklv4.repository.LikeRepository;
import lombok.Getter;

@Getter
public class LikeResponseDto {
    private String msg;

    public LikeResponseDto(String msg){
        this.msg = msg;
    }
}
