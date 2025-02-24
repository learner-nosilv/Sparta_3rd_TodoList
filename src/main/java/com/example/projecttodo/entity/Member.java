package com.example.projecttodo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 1. 무지성으로 갖다 붙이기 5형제 : getter, entity, noargs-, id, genera-
@Getter
@Entity     // JPA의 Entity 관리: 덕분에 DB에 쿼리가 나감
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // id 는 DB가 만들어주므로 생성자 안 만들어도 됨
    public Member(String name) {
        this.name = name;
    }

    public void update(String name){
        this.name = name;
    }
}
