package cl.fp.pokedex.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder,
                                     ClientHttpRequestInterceptor userAgentRequestInterceptor) {
        return restTemplateBuilder
                .additionalInterceptors(userAgentRequestInterceptor)
                .build();
    }
}
