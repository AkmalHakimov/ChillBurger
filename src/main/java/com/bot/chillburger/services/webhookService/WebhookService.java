package com.bot.chillburger.services.webhookService;


import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.lang.annotation.Target;

@Service
public class WebhookService {

    String token = "6727833668:AAG0IzbrQUjv19buC80ic7n4nLyrEwr25bs";

    public Message execute(SendMessage sendMessage) {
        HttpEntity<SendMessage> request = new HttpEntity<>(sendMessage);
        try {
            RestTemplate restTemplate = new RestTemplate();
            Message message = restTemplate.postForObject("https://api.telegram.org/bot" + token + "/sendMessage?chat_id=" + sendMessage.getChatId(), request, Message.class);
            System.out.println(restTemplate);
            return message;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void execute(DeleteMessage deleteMessage) {
        HttpEntity<DeleteMessage> request = new HttpEntity<>(deleteMessage);
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject("https://api.telegram.org/bot" + token + "/deleteMessage?chat_id=" + deleteMessage.getChatId(), request, Object.class);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void execute(EditMessageReplyMarkup editMessageReplyMarkup) {
        HttpEntity<EditMessageReplyMarkup> request = new HttpEntity<>(editMessageReplyMarkup);
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject("https://api.telegram.org/bot" + token + "/editMessageReplyMarkup?chat_id=" + editMessageReplyMarkup.getChatId(), request, Object.class);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void execute(EditMessageCaption editMessageCaption) {
        HttpEntity<EditMessageCaption> request = new HttpEntity<>(editMessageCaption);
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject("https://api.telegram.org/bot" + token + "/editMessageCaption?chat_id=" + editMessageCaption.getChatId(), request, Object.class);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Message execute(SendPhoto sendPhoto) {
        HttpEntity<SendPhoto> request = new HttpEntity<>(sendPhoto);
        try {
            RestTemplate restTemplate = new RestTemplate();
            Message message = restTemplate.postForObject("https://api.telegram.org/bot" + token + "/sendPhoto?chat_id=" + sendPhoto.getChatId(), request, Message.class);
            return message;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
