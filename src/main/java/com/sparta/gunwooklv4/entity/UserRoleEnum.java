package com.sparta.gunwooklv4.entity;

public enum UserRoleEnum {
    USER(Authority.USER), // 일반 사용자
    ADMIN(Authority.ADMIN); // 관리자

    private String authority;

    UserRoleEnum(String authority){
        this.authority = authority;
    }

    public String getAuthority(){
        return this.authority;
    }

    public static class Authority{
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }

}
