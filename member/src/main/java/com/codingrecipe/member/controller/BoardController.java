package com.codingrecipe.member.controller;

import com.codingrecipe.member.dto.BoardDTO;
import com.codingrecipe.member.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class BoardController {
    private final BoardService boardService;


    @GetMapping("/saveB")
    public String saveFormB() {
        return "saveB";
    }

    @PostMapping("/saveB")
    public String saveB(@ModelAttribute BoardDTO boardDTO) {
        System.out.println("boardDTO = " + boardDTO);
        boardService.saveB(boardDTO);
        return "indexB";
    }

    @GetMapping("/indexB")
    public String boardPage() {
        return "indexB";
    }
//
//    public String findAllB(Model modelB) {
//        List<BoardDTO> boardDTOList = boardService.findAllB();
//        modelB.addAttribute("boardList",boardDTOList);
//        return "listB";
//    }

    @GetMapping("/listB")
    public String findAllB(Model modelB) {
        List<BoardDTO> boardDTOList = boardService.findAllB();
        modelB.addAttribute("boardList", boardDTOList);
        return "listB";
    }

}
