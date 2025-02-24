package com.example.projecttodo.controller;

import com.example.projecttodo.dto.MemberRequestDto;
import com.example.projecttodo.dto.MemberResponseDto;
import com.example.projecttodo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

// JSON 방식으로 소통, 통신할거라서 REST라고 붙인다 (response body가 붙은 controller)
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;  // 더 아래 층을 주입받는다

    // 값 저장 C
    @PostMapping("/members")
    public MemberResponseDto save(@RequestBody MemberRequestDto dto) {
        return memberService.save(dto);
    }

    // 다건 조회 R
    @GetMapping("/members")
    public ResponseEntity<List<MemberResponseDto>> findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    // 단건 조회 R
    @GetMapping("/members/{id}")
    public ResponseEntity<MemberResponseDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.findById(id));
    }

    // 수정 U
    @PutMapping("/members/{id}")
    public ResponseEntity<MemberResponseDto> update(
            @PathVariable Long id,
            @RequestBody MemberRequestDto dto)
    {
        return ResponseEntity.ok(memberService.update(id, dto));
    }

    // 삭제 D
    @DeleteMapping("/members/{id}")
    public void delete(@PathVariable Long id) {
        memberService.delete(id);
    }
}
