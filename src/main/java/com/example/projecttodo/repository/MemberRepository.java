package com.example.projecttodo.repository;

import com.example.projecttodo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository 는 JpaRepo 에서 주입받으니깐 안해도 됨
public interface MemberRepository extends JpaRepository<Member, Long> {

}
