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
    private Integer id;

    private String title;

    private String content;

    private Timestamp regdate; //생성 날짜

    private Timestamp updatedate; //수정 날짜

    private Long view;

    private String image;

    private String category;

    public BoardEntity toEntity() {

        BoardEntity boardEntity = BoardEntity.builder()
                .id(id)
                .title(title)
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
