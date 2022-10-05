package com.example.Bowling.domain.entity;

import com.example.Bowling.domain.dto.ReplyDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reply")
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rno; //댓글 번호

    private Integer bno; //게시물 번호

    private String writer;
    
    private String content;
    
    private Timestamp regdate; //생성 날짜
    
    private Timestamp updatedate; //수정 날짜

    private Long grp; //그룹 대댓글용

    private Long grpl; //그룹 댓글의 깊이 (댓글 : 0, 답글 : 1)

    public ReplyDTO toDTO (){

        ReplyDTO replyDTO = ReplyDTO.builder()
                .rno(rno)
                .bno(bno)
                .writer(writer)
                .content(content)
                .regdate(regdate)
                .updatedate(updatedate)
                .grp(grp)
                .grpl(grpl)
                .build();

        return replyDTO;
    }
}
