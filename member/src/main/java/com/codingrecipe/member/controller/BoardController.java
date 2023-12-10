package com.codingrecipe.member.controller;

import com.codingrecipe.member.dto.BoardDTO;
import com.codingrecipe.member.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public String saveB(@ModelAttribute BoardDTO boardDTO) throws IOException {
        System.out.println("boardDTO = " + boardDTO);
        boardService.saveB(boardDTO);
        return "redirect:/member/paging";
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
        return "paging";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model,
                           @PageableDefault(page=1) Pageable pageable) {
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        model.addAttribute("page",pageable.getPageNumber());
        return "detailB";
    }

    @GetMapping("/updateB/{id}")
    public String updateFormB(@PathVariable Long id, Model model,
                              @PageableDefault(page=1) Pageable pageable) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardUpdate", boardDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "updateB";
    }

    @PostMapping("/updateB")
    public String updateB(@ModelAttribute BoardDTO boardDTO, Model model) {
        BoardDTO board = boardService.updateB(boardDTO);
        model.addAttribute("board", board);
        return "detailB";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/member/paging";
    }

    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<BoardDTO> boardList = boardService.paging(pageable);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ?
                startPage + blockLimit - 1 : boardList.getTotalPages();

        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "paging";

    }
}
