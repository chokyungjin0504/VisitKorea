package com.codingrecipe.member.controller;

import com.codingrecipe.member.dto.BoardDTO;
import com.codingrecipe.member.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping("/indexB")
//    public String boardPage() {
//        return "indexB";
//    }
//
//    public String findAllB(Model modelB) {
//        List<BoardDTO> boardDTOList = boardService.findAllB();
//        modelB.addAttribute("boardList",boardDTOList);
//        return "listB";
//    }

    @GetMapping("/indexB")
    public String findAllB(Model modelB) {
        List<BoardDTO> boardDTOList = boardService.findAllB();
        modelB.addAttribute("boardList", boardDTOList);
        return "indexB";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "detailB";
    }

    @GetMapping("/updateB/{id}")
    public String updateFormB(@PathVariable Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardUpdate", boardDTO);
        return "updateB";
    }

    @PostMapping("/updateB")
    public String updateB(@ModelAttribute BoardDTO boardDTO, Model model) {
        BoardDTO board = boardService.updateB(boardDTO);
        model.addAttribute("board", board);
        return "detailB";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect";
    }
}
