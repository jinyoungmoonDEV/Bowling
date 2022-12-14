package com.example.Bowling.service.board;

import com.example.Bowling.domain.dto.BoardDTO;
import com.example.Bowling.domain.dto.BoardDetailDTO;
import com.example.Bowling.domain.entity.BoardEntity;
import com.example.Bowling.repository.BoardRepository;
import com.example.Bowling.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    private final ReplyRepository replyRepository;

    @Override
    public BoardEntity create(BoardDTO boardDTO) {

        boardDTO.setRegdate(Timestamp.valueOf(LocalDateTime.now()));

        return boardRepository.save(boardDTO.toEntity());
    }

    @Override
    public BoardEntity findOne(Integer id) {
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
    public BoardEntity update(BoardDTO boardDTO, Integer id) {

        BoardEntity find = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Board Does Not Exist"));
        BoardDTO change = find.toDTO();

        change.setTitle(boardDTO.getTitle());
        change.setContent(boardDTO.getContent());
        change.setUpdatedate(Timestamp.valueOf(LocalDateTime.now()));
        change.setImage(boardDTO.getImage());
        change.setCategory(boardDTO.getCategory());

        return boardRepository.save(change.toEntity());
    }

    @Override
    public String delete(Integer id) {
        boardRepository.deleteById(id);
        return "Delete Success";
    }
}
