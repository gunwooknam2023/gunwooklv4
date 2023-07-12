package com.sparta.gunwooklv4.dto;

import lombok.Getter;

@Getter
public class StatusResult {
    private String msg; // 메시지
    private int code; // 응답코드

    public StatusResult(String msg, int code){
        this.msg = msg;
        this.code = code;
    }

}
