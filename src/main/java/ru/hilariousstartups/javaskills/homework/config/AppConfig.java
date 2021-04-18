package ru.hilariousstartups.javaskills.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;


@Configuration
public class AppConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ru.hilariousstartups.javaskills.homework.model.generated");
        return marshaller;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
