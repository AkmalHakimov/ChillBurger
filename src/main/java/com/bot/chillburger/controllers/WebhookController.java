package com.bot.chillburger.controllers;


import com.bot.chillburger.telegramBot.AppBot;
import com.bot.chillburger.telegramBot.MyBot;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@RequiredArgsConstructor
public class WebhookController {

    private final MyBot myBot;

        @RequestMapping(value = "/callback/update",method = RequestMethod.POST)
    public void onUpdateReceived(@RequestBody Update update){
         myBot.onUpdateReceived(update);
    }
}
