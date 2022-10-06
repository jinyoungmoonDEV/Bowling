package com.example.Bowling.service.board;

import com.example.Bowling.domain.dto.BoardDTO;
import com.example.Bowling.domain.entity.BoardEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {
    BoardEntity create(BoardDTO boardDTO);
    BoardEntity findOne(Long id);
    List<BoardEntity> findAll();
    BoardEntity findByCategory(String category);
    BoardEntity update(BoardDTO boardDTO, Long id);
    String delete(Long id);
}
