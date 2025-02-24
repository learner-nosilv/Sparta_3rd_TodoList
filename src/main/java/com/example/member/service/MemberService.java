package com.example.member.service;

import com.example.member.dto.MemberResponseDto;
import com.example.member.dto.MemberUpdateRequestDto;
import com.example.member.entity.Member;
import com.example.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 다건조회
    @Transactional
    public List<MemberResponseDto> findAll(){
        List<Member> members = memberRepository.findAll();  // 엔티티
        return members.stream().map(member -> new MemberResponseDto(member.getId(), member.getEmail())).toList();
    }

    // 단건조회
    @Transactional
    public MemberResponseDto findById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("해당 회원은 없습니다.")
        );
        return new MemberResponseDto( member.getId(), member.getEmail());
    }

    // 단건수정
    @Transactional
    public void update(Long memberId, MemberUpdateRequestDto memberUpdateRequestDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("해당 회원은 없습니다.")
        );
    }

    // 단건삭제
    @Transactional
    public void deleteById(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}