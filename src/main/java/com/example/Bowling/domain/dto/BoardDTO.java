package com.example.Bowling.domain.dto;

import com.example.Bowling.domain.entity.BoardEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BoardDTO {
    private Long id;

    private String title;

    private String content;

    public BoardEntity toEntity() {
        BoardEntity boardEntity = BoardEntity.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
        return boardEntity;
    }
}
