package com.hxl.spring.ai.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * 聊天客户端控制
 *
 * @author hengxiaoliang
 */
@RestController
public class ChatClientController {

    @Resource
    private ChatModel dashScopeModel;

    @Resource
    private ChatClient dashScopeClient;

    @GetMapping("/model")
    public Flux<String> model(@RequestParam("msg") String msg) {
        return dashScopeModel.stream(msg);
    }

    @GetMapping("/client")
    public Flux<String> client(@RequestParam("msg") String msg) {
        return dashScopeClient.prompt().user(msg).stream().content();
    }
}
