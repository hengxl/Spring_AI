package com.hxl.spring.ai.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * 提示词控制器
 *
 * @author hxl
 */
@RestController
public class PromptController {

    @Resource
    private ChatClient deepseekClient;

    @GetMapping("/system")
    public Flux<String> systemPrompt(@RequestParam("msg") String msg) {
        return deepseekClient.prompt()
                .system("你是一个医生，只允许回答医学方面是知识，其他的知识都说无可奉告！")
                .user(msg)
                .stream()
                .content();
    }

    @GetMapping("/user")
    public Flux<String> userPrompt(@RequestParam("msg") String msg) {
        // 创建一个系统消息
        SystemMessage systemMessage = new SystemMessage("你是一个JAVA程序员，只允许回答JAVA方面是知识，其他的知识都说无可奉告！");
        // 创建一个用户消息
        UserMessage userMessage = new UserMessage(msg);
        // 创建一个提示词
        Prompt prompt = new Prompt(systemMessage, userMessage);

        return deepseekClient.prompt(prompt).stream().content();
    }

    @GetMapping("/assistant")
    public Flux<ChatResponse> assistantPrompt(@RequestParam("msg") String msg) {
        // 创建一个系统消息
        SystemMessage systemMessage = new SystemMessage("你是一个英语，只允许回答英语方面是知识，其他的知识都说无可奉告！");
        // 创建一个用户消息
        UserMessage userMessage = new UserMessage(msg);
        // 创建一个助手消息
        AssistantMessage assistantMessage = new AssistantMessage("你回答的字数不能超过20个字");
        // 创建一个提示词
        Prompt prompt = new Prompt(systemMessage, userMessage, assistantMessage);

        return deepseekClient.prompt(prompt).stream().chatResponse();
    }

    @GetMapping("/all")
    public Flux<String> allPrompt(@RequestParam("msg") String msg) {
        return deepseekClient.prompt()
                .system("你是一个外卖骑手，允许回答外卖方面是知识，其他的知识都说无可奉告！只能说20字以内")
                .user(msg)
                .stream()
                .content();
    }
}
