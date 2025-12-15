package com.hxl.spring.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 聊天客户端
 * TODO: ChatClient不支持自动注入
 *
 * @author hengxiaoliang
 */
@RestController
public class ChatClientController {

    /**
     * 通过构造函数注入，ChatClient并没有直接的Bean，还是需要依赖 ChatModel实例对象去构建
     */
    private final ChatClient dashScopeClientByConstructor;

    public ChatClientController(ChatModel dashScopeChatModel) {
        this.dashScopeClientByConstructor = ChatClient.builder(dashScopeChatModel).build();
    }

    @GetMapping("/one")
    public String one(@RequestParam("msg") String msg) {
        return dashScopeClientByConstructor.prompt()
                .user(msg).call().content();
    }
}
