package io.learning.socialinteractionai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebclientConfiguration {

    @Bean
    public WebClient imageCreationWebclient(){
        return WebClient.builder()
                .baseUrl("http://127.0.0.1:7860")
                .defaultHeader("Content-Type", "application/json")
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
                .build();
    }
}
