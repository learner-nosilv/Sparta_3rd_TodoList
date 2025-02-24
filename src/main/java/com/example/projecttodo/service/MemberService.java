package com.example.projecttodo.service;

import com.example.projecttodo.dto.MemberRequestDto;
import com.example.projecttodo.dto.MemberResponseDto;
import com.example.projecttodo.entity.Member;
import com.example.projecttodo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    // 저장하는 것

    @Transactional
    public MemberResponseDto save(MemberRequestDto dto) {
        Member member = new Member(dto.getName());
        Member savedMember = memberRepository.save(member);

        return new MemberResponseDto(savedMember.getId(),savedMember.getName());

    }

    // @Transactional(readOnly = true) -> 오류남
    @Transactional
    public List<MemberResponseDto> findAll(){
        List<Member> members = memberRepository.findAll();  // 엔티티
        List<MemberResponseDto> dtos = new ArrayList<>();   // DTO

        // 엔티티 내용을 dto 리스트에 담기
        for (Member member : members) {
            dtos.add (new MemberResponseDto(member.getId(),member.getName()));
        }

        return dtos;
    }

    // @Transactional(readOnly = true) -> 오류남
    @Transactional
    public MemberResponseDto findById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("Member not found")
        );
        // id 는 optional

        // dto 형태로 리턴
        return new MemberResponseDto(member.getId(),member.getName());
    }

    @Transactional
    public MemberResponseDto update(Long id, MemberRequestDto dto) {
        Member member = memberRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("Member not found")
        );

        //Entity를 받는 순간 JPA 가 업데이트 해줌
        member.update(dto.getName());   // 영속성 컨텍스트 <- 복사 안하고 진짜로 고쳐줌
        memberRepository.save(member);  // 다시 해주면 JPA에의 의존성을 조금 떨친다고 하는데 굳이
        return new MemberResponseDto(member.getId(),member.getName());
    }

    @Transactional
    public void delete(Long id) {
        boolean isExist = memberRepository.existsById(id);
        if(isExist == false){
            throw new IllegalArgumentException("그런 아이디를 가진 회원이 없어요");
        }
        memberRepository.deleteById(id);
    }
}
