package com.example.auth.service;

import com.example.auth.dto.AuthLoginRequestDto;
import com.example.auth.dto.AuthLoginResponseDto;
import com.example.auth.dto.AuthSignupRequestDto;
import com.example.member.entity.Member;
import com.example.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;

    @Transactional
    public void signup(AuthSignupRequestDto authSignupRequestDto) {
        Member member = new Member(authSignupRequestDto.getEmail());
        memberRepository.save(member);
    }

    @Transactional
    public AuthLoginResponseDto login(AuthLoginRequestDto authLoginRequestDto) {
        Member member = memberRepository.findByEmail(authLoginRequestDto.getEmail()).orElseThrow(
                () -> new IllegalStateException("해당 멤버가 없습니다.")
        );
        return new AuthLoginResponseDto(member.getId());
    }
}
