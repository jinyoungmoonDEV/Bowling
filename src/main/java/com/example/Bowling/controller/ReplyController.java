package com.example.Bowling.controller;

import com.example.Bowling.service.reply.ReplyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyServiceImpl replyService;


}
