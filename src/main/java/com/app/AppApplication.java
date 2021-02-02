package com.app;

import com.app.twilio.Service;
import com.app.twilio.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class AppApplication {
    @Autowired
    private static Service service;

    @Bean
    RestTemplate getTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);

        SmsRequest request=new SmsRequest("+212708042169","Hello first app");
        service.sendSms(request);
    }




}
