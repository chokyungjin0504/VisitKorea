package com.codingrecipe.member.entity;

import com.codingrecipe.member.dto.MemberDTO;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "member_table") //table 이름
//Entity class = table 역할
public class MemberEntity {
    @Id //Table의 PRIMARY KEY 컬럼 정의, pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment 지정
    private Long id;

    //일반 컬럼 지정
    @Column(unique = true) //unique 제약조건 추가
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    //Entity를 객체로 만들어서 호출하는 메서드가 아닌, static 메서드로 정의 -> 요즘 이 방법을 많이 씀
    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        return memberEntity;
    }

    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        return memberEntity;
    }
}
