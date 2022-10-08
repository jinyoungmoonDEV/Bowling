package com.example.Bowling.service.board;

import com.example.Bowling.domain.dto.BoardDTO;
import com.example.Bowling.domain.entity.BoardEntity;

import java.util.List;

public interface BoardService {
    BoardEntity create(BoardDTO boardDTO);
    BoardEntity findOne(Integer id);
    List<BoardEntity> findAll();
    BoardEntity findByCategory(String category);
    BoardEntity update(BoardDTO boardDTO, Integer id);
    String delete(Integer id);
}
