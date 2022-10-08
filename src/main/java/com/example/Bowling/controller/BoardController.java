package com.example.Bowling.controller;

import com.example.Bowling.domain.dto.BoardDTO;
import com.example.Bowling.service.board.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardServiceImpl boardService;

    @PostMapping("/board")
    public ResponseEntity createBoard(@RequestBody BoardDTO boardDTO){
        return ResponseEntity.created(URI.create("/boards/new")).body(boardService.create(boardDTO));
    }

    @GetMapping("/board")
    public ResponseEntity findAllBoard(){
        return ResponseEntity.ok().body(boardService.findAll());
    }

    @GetMapping("/board/{category}")
    public ResponseEntity findByCategory(@PathVariable String category) {
        return ResponseEntity.ok().body(boardService.findByCategory(category));
    }

    @GetMapping("/board/{id}")
    public ResponseEntity findOneBoard(@PathVariable Integer id){
        return ResponseEntity.ok().body(boardService.findOne(id));
    }

    @PutMapping("/board/{id}")
    public ResponseEntity updateBoard(@RequestBody BoardDTO boardDTO ,@PathVariable Integer id){
        return ResponseEntity.created(URI.create("/boards/board/update")).body(boardService.update(boardDTO, id));
    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity deleteBoard(@PathVariable Integer id){
        return ResponseEntity.ok().body(boardService.delete(id ));
    }
}
