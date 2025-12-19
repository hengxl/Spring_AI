package com.hxl.spring.ai.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

/**
 * 动态提示词
 *
 * @author hxl
 */
@RestController
public class DynamicPromptController {

    @Resource
    private ChatClient deepseekClient;

    @Resource
    private ChatClient qwenClient;

    // TODO: 直接解析资源路径必须要 ${}！ ${}是去配置文件里查找key对应的值，而key为${key}括号里的内容
    @Value("classpath:/template/prompt/java.txt")
    private org.springframework.core.io.Resource userTemplate;

    @GetMapping("/chat1")
    public Flux<String> chat1(@RequestParam("type") String type
            , @RequestParam("size") String size,
                              @RequestParam("msg") String msg) {
        // 通过模板文件创建提示词
        PromptTemplate promptTemplate = new PromptTemplate(userTemplate);
        // 填充模板参数
        Prompt prompt = promptTemplate.create(Map.of("type", type, "size", size));

        return qwenClient.prompt(prompt).user(msg).stream().content();
    }

}
