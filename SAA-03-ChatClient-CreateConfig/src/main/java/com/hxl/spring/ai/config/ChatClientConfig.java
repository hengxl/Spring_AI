package com.hxl.spring.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 聊天客户端配置
 *
 * @author hengxiaoliang
 */
@Configuration
public class ChatClientConfig {

    /**TODO: ChatClient是接口，且没有继承 Model类，不能自动注入，需要定制化
     *  在配置类里创建ChatClient的Bean
     *  需要依赖ChatModel实例对象去构建
     */
    @Bean
    public ChatClient dashScopeClient(ChatModel dashScopeModel) {
        return ChatClient.builder(dashScopeModel).build();
    }
}
