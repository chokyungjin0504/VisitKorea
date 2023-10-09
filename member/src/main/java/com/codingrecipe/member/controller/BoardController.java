package com.codingrecipe.member.controller;

import com.codingrecipe.member.dto.BoardDTO;
import com.codingrecipe.member.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/indexB")
    public String boardPage() {
        return "indexB";
    }

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


}
