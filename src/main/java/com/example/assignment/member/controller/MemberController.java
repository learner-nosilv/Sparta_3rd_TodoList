package com.example.member.controller;

import com.example.common.consts.Const;
import com.example.member.dto.MemberResponseDto;
import com.example.member.dto.MemberUpdateRequestDto;
import com.example.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 다건조회
    @GetMapping("/members")
    public ResponseEntity<List<MemberResponseDto>> getAll(){
        return ResponseEntity.ok(memberService.findAll());
    }

    // 단건조회
    @GetMapping("/members/{memberId}")
    public ResponseEntity<MemberResponseDto> getOne(@PathVariable Long memberId){
        return ResponseEntity.ok(memberService.findById(memberId));
    }

    //
    @PutMapping("/members")
    public void update(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @RequestBody MemberUpdateRequestDto memberUpdateRequestDto )
    {
        memberService.update(memberId, memberUpdateRequestDto);
    }

    @DeleteMapping("/members")
    public void delete(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId )
    {
        memberService.deleteById(memberId);
    }

}
