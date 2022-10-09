package com.example.Bowling.domain.dto;

import com.example.Bowling.domain.entity.BoardEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class BoardDTO {
    private Integer id; //게시물 번호

    private String writer; //게시물 작성자

    private String title; //게시물 제목

    private String content; //게시물 내용

    private Timestamp regdate; //생성 날짜

    private Timestamp updatedate; //수정 날짜

    private Long view; //조회수

    private String image; //게시물 이미지 url

    private String category; //게시물 카테고리

    public BoardEntity toEntity() {

        BoardEntity boardEntity = BoardEntity.builder()
                .id(id)
                .title(title)
                .writer(writer)
                .content(content)
                .regdate(regdate)
                .updatedate(updatedate)
                .view(view)
                .image(image)
                .category(category)
                .build();

        return boardEntity;
    }
}
