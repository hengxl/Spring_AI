package com.hxl.spring.ai.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * AI项目控制器
 *
 * @author hxl
 */
@RestController
public class AIProjectController {

    @Resource
    private ChatClient deepseekClient;

    @Resource
    private ChatClient qwenClient;

    @GetMapping("/deepseek")
    public Flux<String> deepseek(@RequestParam("msg") String msg) {
        return deepseekClient.prompt()
                .user(msg)
                .stream()
                .content();
    }

    @GetMapping("/qwen")
    public Flux<String> qwen(@RequestParam("msg") String msg) {
        return qwenClient.prompt()
                .user(msg)
                .stream()
                .content();
    }

}
