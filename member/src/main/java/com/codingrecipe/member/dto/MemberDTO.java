//회원정보 저장하고 필드를 프라이빗으로 정의 -> Getter와 Setter로 간접적으로 필드 접근(Lombok이 자동으로 만들어줌)
package com.codingrecipe.member.dto;

import com.codingrecipe.member.entity.MemberEntity;
import lombok.*;

//Lombok 라이브러리가 제공해주는 annotation
@Getter
@Setter
@NoArgsConstructor //기본생성자 자동으로 만들어줌
@AllArgsConstructor //필드를 모두 매개변수로 하는 생성자를 만들어줌
@ToString //DTO의 출력을 ToString으로 자동 완성
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        return memberDTO;//
    }
}
