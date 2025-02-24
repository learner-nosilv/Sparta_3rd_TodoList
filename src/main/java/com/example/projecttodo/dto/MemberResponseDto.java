package com.example.projecttodo.dto;

import lombok.Getter;

@Getter
public class MemberResponseDto {

    private final Long id;  // 왜 final? 응답은 변경될 일 없으니까
    private final String name;

    public MemberResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
