package com.bot.chillburger.telegramBot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class MyBot {

    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getText());
    }
}
