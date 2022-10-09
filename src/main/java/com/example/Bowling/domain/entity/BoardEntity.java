package com.example.Bowling.domain.entity;

import com.example.Bowling.domain.dto.BoardDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id; //게시물 번호

    private String writer; //게시물 작성자

    private String title; //게시물 제목

    private String content; //게시물 내용

    private Timestamp regdate; //등록 일자

    private Timestamp updatedate; //수정일자

    private Long view; //조회수

    private String image; //게시물 이미지 url

    private String category; //게시물 카테고리

    public BoardDTO toDTO(){

        BoardDTO boardDTO = BoardDTO.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .content(content)
                .regdate(regdate)
                .updatedate(updatedate)
                .view(view)
                .image(image)
                .category(category)
                .build();

        return boardDTO;
    }
}
