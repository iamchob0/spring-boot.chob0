package com.iam.chobo.domain.slack;

import com.iam.chobo.global.slack.SlackMessageDto;
import com.iam.chobo.global.slack.SlackSenderManager;
import com.iam.chobo.global.slack.SlackTarget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="slack", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class SlackController {
    @Autowired
    private SlackSenderManager slackSenderManager;

    @PostMapping("/basic")
    public void basic(@RequestBody SlackMessageDto.Basic dto) {
        slackSenderManager.send(dto);
    }

    @PostMapping("/attachment")
    public void attachment(@RequestBody SlackMessageDto.Attachments dto) {
        slackSenderManager.send(dto);
    }

    @PostMapping("/button")
    public void button(@RequestBody SlackMessageDto.MessageButtons dto) {
        slackSenderManager.send(dto);
    }
}