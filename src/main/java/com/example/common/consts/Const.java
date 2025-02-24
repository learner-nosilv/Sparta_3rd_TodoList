package com.example.common.config.consts;

public abstract class Const {
    // abstract 를 쓰는 이유는 new 로 객체가 되는 것을 방지하기 위함
    // interface 를 안 쓰는 이유는 목적 때문, interface는 객체의 행동을 정의할 목적으로 사용하므로
    // 단순한 상수 저장소로 사용하기에는 목적 상 맞지 않음
    public static final String LOGIN_MEMBER ="LOGIN_MEMBER";
}
