package com.example.Bowling.service.board;

import com.example.Bowling.domain.dto.BoardDTO;
import com.example.Bowling.domain.entity.BoardEntity;
import com.example.Bowling.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public BoardEntity create(BoardDTO boardDTO) {
        return boardRepository.save(boardDTO.toEntity());
    }

    @Override
    public BoardEntity findOne(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Board Does Not Exist"));
    }

    @Override
    public List<BoardEntity> findAll() {
        return boardRepository.findAll();
    }

    @Override
    public BoardEntity findByCategory(String category) {
        return boardRepository.findByCategory(category);
    }

    @Override
    public BoardEntity update(BoardDTO boardDTO, Long id) {
        BoardEntity find = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Board Does Not Exist"));
        BoardDTO change = find.toDTO();
        change.setTitle(boardDTO.getTitle());
        change.setContent(boardDTO.getContent());
        return boardRepository.save(change.toEntity());
    }

    @Override
    public String delete(Long id) {
        boardRepository.deleteById(id);
        return "Delete Success";
    }
}
