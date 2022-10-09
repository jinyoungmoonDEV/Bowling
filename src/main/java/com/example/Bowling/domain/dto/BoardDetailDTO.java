package com.example.Bowling.domain.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDetailDTO {
    
    private Integer bno; //게시물 번호

    private String writer; //게시물 작성자

    private String title; //게시물 제목

    private String content; //게시물 내용

    private Timestamp regdate; //생성 날짜

    private Timestamp updatedate; //수정 날짜

    private Long view; //조회수

    private String image; //게시물 이미지 url

    private String category; //게시물 카테고리

    /*----------------------------------------------------------------------------*/
    // 댓글

    private Integer rno; //댓글 번호

    private String commentWriter; //댓글 작성자

    private String comment; //댓글 내용

    private Timestamp commentRegdate; //생성 날짜

    private Timestamp commentUpdatedate; //수정 날짜

    private Long grp; //그룹 대댓글용

    private Long grpl; //그룹 댓글의 깊이 (댓글 : 0, 답글 : 1)
}
