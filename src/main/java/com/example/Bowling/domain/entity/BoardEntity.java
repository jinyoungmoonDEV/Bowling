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
    private Integer id;

    private String writer;

    private String title;

    private String content;

    private Timestamp regdate; //등록 일자

    private Timestamp updatedate; //수정일자

    private Long view;

    private String image;

    private String category;

    public BoardDTO toDTO(){

        BoardDTO boardDTO = BoardDTO.builder()
                .id(id)
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
