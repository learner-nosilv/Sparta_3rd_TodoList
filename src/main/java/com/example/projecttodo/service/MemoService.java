package com.example.projecttodo.service;

import com.example.projecttodo.dto.MemoRequestDto;
import com.example.projecttodo.dto.MemoResponseDto;
import com.example.projecttodo.entity.Memo;
import com.example.projecttodo.repository.MemoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponseDto save(MemoRequestDto dto) {
        Memo memo = new Memo(dto.getContent());
        Memo savedMemo = memoRepository.save(memo);

        return new MemoResponseDto(savedMemo.getId(), savedMemo.getContent());
    }

    @Transactional
    public List<MemoResponseDto> findAll() {
        List<Memo> memos =memoRepository.findAll();

        List<MemoResponseDto> dtos = new ArrayList<>();
        for (Memo memo : memos) {
            MemoResponseDto dto = new MemoResponseDto(memo.getId(), memo.getContent());
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional
    public MemoResponseDto findById(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Memo not found")
        );
        return new MemoResponseDto(memo.getId(), memo.getContent());
    }

    @Transactional
    public MemoResponseDto update(long id, MemoRequestDto dto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Memo not found")
        );
        memo.update(dto.getContent());
        return new MemoResponseDto(memo.getId(), memo.getContent());
    }

    @Transactional
    public void deleteById(Long id) {
        if (memoRepository.existsById(id) == false) {
            throw new IllegalArgumentException("Memo not found");
        }
        memoRepository.deleteById(id);
    }
}
