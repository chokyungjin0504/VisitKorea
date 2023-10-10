package com.codingrecipe.member.service;

import com.codingrecipe.member.dto.BoardDTO;
import com.codingrecipe.member.entity.BoardEntity;
import com.codingrecipe.member.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public void saveB(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }
}
