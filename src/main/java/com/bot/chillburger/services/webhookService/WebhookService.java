package com.bot.chillburger.services.webhookService;


import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.lang.annotation.Target;

@Service
public class WebhookService {

    String token = "6727833668:AAG0IzbrQUjv19buC80ic7n4nLyrEwr25bs";

    public Message execute(SendMessage sendMessage) {
        HttpEntity<SendMessage> request = new HttpEntity<>(sendMessage);
        try {
            RestTemplate restTemplate = new RestTemplate();
            Message message = restTemplate.postForObject("https://api.telegram.org/bot" + token + "/sendMessage", request, Message.class);
            return message;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
