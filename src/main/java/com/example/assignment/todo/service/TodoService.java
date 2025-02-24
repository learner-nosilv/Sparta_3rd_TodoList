package com.example.todo.service;

import com.example.member.entity.Member;
import com.example.member.repository.MemberRepository;
import com.example.todo.dto.*;
import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public TodoSaveResponseDto save(Long memberId, TodoSaveRequestDto dto){
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("해당 멤버가 없습니다.")
        );

        Todo todo = new Todo( dto.getContent(), member );

        Todo saveTodo = todoRepository.save(todo);
        return new TodoSaveResponseDto(
                saveTodo.getId(),
                saveTodo.getContent(),
                member.getId(),
                member.getEmail()
        );
    }

    @Transactional
    public List<TodoResponseDto> findAll(){
        List<Todo> todos = todoRepository.findAll();
        List<TodoResponseDto> dtos = new ArrayList<>();
        for (Todo todo : todos) {
            dtos.add(new TodoResponseDto(
                    todo.getId(),
                    todo.getContent()
            ));
        }
        return dtos;
    }

    @Transactional
    public TodoResponseDto findById(Long todoId){
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalStateException("해당 Todo 는 없습니다.")
        );
        return new TodoResponseDto( todo.getId(), todo.getContent());
    }

    @Transactional
    public TodoUpdateResponseDto update(Long memberId, Long todoId, TodoUpdateRequestDto dto){
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("해당 멤버는 없습니다.")
        );

        Todo todo = todoRepository.findById(todoId).orElseThrow(
                ()-> new IllegalStateException("해당 Todo 는 없습니다.")
        );

        if(!todo.getMember().getId().equals(member.getId())){
            throw new IllegalStateException("해당 Todo의 작성자가 아닙니다.");
        }

        todo.update(dto.getContent());
        return new TodoUpdateResponseDto(
                todo.getId(),
                todo.getContent()
        );
    }

    @Transactional
    public void deletedById(Long memberId, Long todoId){
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("해당 id 의 멤버는 없습니다.")
        );

        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalStateException("해당 id 의 todo는 없습니다.")
        );

        if(!todo.getMember().getId().equals(member.getId())){
            throw new IllegalStateException("해당 Todo의 작성자가 아닙니다.");
        }

        todoRepository.deleteById(todoId);
    }
}
