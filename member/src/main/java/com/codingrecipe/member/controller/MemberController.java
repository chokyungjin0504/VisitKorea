package com.codingrecipe.member.controller;

import com.codingrecipe.member.dto.MemberDTO;
import com.codingrecipe.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    //생성자 주입
    private final MemberService memberService;

    @GetMapping("/member/index")
    public String mainPage() {
        return "index";
    }

    //memberService 필드를 매개변수로 하는 controller 생성자를 @RequiredArgsConstructor 어노테이션이 만들어줌
    //MemberController class에 대한 객체를 스프링 빈으로 등록할 때 자동적으로 서비스 클래스에 대한 객체를 주입 받음
    //주입 받는다? Controller class가 Service class의 자원으로 쓰는 권한 생김

    // 회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/member/save")
//    @RequestParam("memberEmail") String memberEmail,
//    @RequestParam("memberPassword") String memberPassword,
//    @RequestParam("memberName") String memberName
    //위의 것들을 @ModelAttribute로 대체
    public String save(@ModelAttribute MemberDTO memberDTO) {
        //@RequestParmam: memberEmail에 담겨있는 값을 memberEmail로 옮긴다
        System.out.println("MemberController.save");
        // soutm: 현재 메서드가 뭔지 프린트문으로 작성해줌
        System.out.println("memberDTO = " + memberDTO);
        // soutp: 매게변수를 프린트문으로 자동완성
        memberService.save(memberDTO);
        return "login";
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }
    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            //login 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "main";
        } else {
            //login 실패
            return "login";
        }
    }

    //링크클릭? -> get
    @GetMapping("/member/list")
    //Spring에서 실어나르는 것: Model
    public String findAll(Model model) {
        //DB의 모든 데이터 가져오기, 데이터를 가져올 때는 list를 많이 씀
        List<MemberDTO> memberDTOList = memberService.findAll();
        //어떠한 html로 가져갈 데이터가 있다면 model 사용
        model.addAttribute("memberList", memberDTOList);
        return "list";
    }

    //{}안의 값을 취하겠다
//    @GetMapping("/member/{id}")
//    //경로 상에 있는 것을 가져올 때: PathVariable
//    public String findById(@PathVariable Long id, Model model) {
//        MemberDTO memberDTO = memberService.findById(id);
//        model.addAttribute("member",memberDTO);
//        return "detail";//
//    }

    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model) {
        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.updateForm(myEmail);
        model.addAttribute("updateMember", memberDTO);
        return "update";
    }

    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        memberService.update(memberDTO);
        return "redirect:/member/" + memberDTO.getId();
    }

    @GetMapping("/member/logout")
    //session 이용
    public String logout(HttpSession session) {
        //session 무효화
        session.invalidate();
        return "index";
    }
}
