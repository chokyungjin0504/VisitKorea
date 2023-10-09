package com.codingrecipe.member.repository;

import com.codingrecipe.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

//MemberRepository는 JpaRepository를 상속받음<entity class의 이름, entity class의 pk타입>
//repository 작업시 반드시 entity 객체로 받아야 함
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //이메일로 회원 정보 조회 (select * from member_table where member_email=?)
    //Entity 형태로 받고 넘겨주어야하는 값은 memberEmail
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
