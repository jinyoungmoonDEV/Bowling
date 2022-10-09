package com.example.Bowling.domain.dto;

import com.example.Bowling.domain.entity.ReplyEntity;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {

    private Integer rno; //댓글 번호

    private Integer bno; //게시물 번호

    private String writer; //게시물 작성자

    private String content; //게시물 내용

    private Timestamp regdate; //생성 날짜

    private Timestamp updatedate; //수정 날짜

    private Long grp; //그룹 대댓글용

    private Long grpl; //그룹 댓글의 깊이 (댓글 : 0, 답글 : 1)

    public ReplyEntity toEntity (){

        ReplyEntity replyEntity = ReplyEntity.builder()
                .rno(rno)
                .bno(bno)
                .writer(writer)
                .content(content)
                .regdate(regdate)
                .updatedate(updatedate)
                .grp(grp)
                .grpl(grpl)
                .build();

        return replyEntity;
    }
}
