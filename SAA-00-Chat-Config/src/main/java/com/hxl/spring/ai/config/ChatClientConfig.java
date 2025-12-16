package com.hxl.spring.ai.config;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 聊天客户端配置
 *
 * @author hxl
 */
@Configuration
public class ChatClientConfig {

    @Value("${spring.ai.dashscope.api-key}")
    private String apiKey;

    private static final String DEEPSEEK = "deepseek-v3.2";
    private static final String QWEN = "qwen3-max";

    /**
     * 注入 deepseek 模型
     */
    @Bean
    public ChatModel deepseekModel() {
        return DashScopeChatModel.builder()
                .dashScopeApi(DashScopeApi.builder().apiKey(apiKey).build())
                .defaultOptions(DashScopeChatOptions.builder().withModel(DEEPSEEK).build())
                .build();
    }

    /**
     * 注入 qwen 模型
     */
    @Bean
    public ChatModel qwenModel() {
        return DashScopeChatModel.builder()
                .dashScopeApi(DashScopeApi.builder().apiKey(apiKey).build())
                .defaultOptions(DashScopeChatOptions.builder().withModel(QWEN).build())
                .build();
    }

    /**
     * 创建 deepseek 模型客户端
     */
    @Bean
    public ChatClient deepseekClient(@Qualifier("deepseekModel") ChatModel deepseekModel) {
        return ChatClient.builder(deepseekModel).build();
    }

    /**
     * 创建 qwen 模型客户端
     */
    @Bean
    public ChatClient qwenClient(@Qualifier("qwenModel") ChatModel qwenModel) {
        return ChatClient.builder(qwenModel).build();
    }

}
