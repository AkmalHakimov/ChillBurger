package com.bot.chillburger.telegramBot;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class AppBot extends TelegramLongPollingBot {


    @SneakyThrows
    @Autowired
    public AppBot(TelegramBotsApi api) {
        api.registerBot(this);
    }


    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("salom");
    }

    @Override
    public String getBotUsername() {
        return "t.me/chill_burger_bot";
    }

    @Override
    public String getBotToken() {
        return "6727833668:AAG0IzbrQUjv19buC80ic7n4nLyrEwr25bs";
    }
}
