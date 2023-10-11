package com.codingrecipe.member.service;

import com.codingrecipe.member.dto.BoardDTO;
import com.codingrecipe.member.entity.BoardEntity;
import com.codingrecipe.member.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public void saveB(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }

    public List<BoardDTO> findAllB() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity: boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }
}
