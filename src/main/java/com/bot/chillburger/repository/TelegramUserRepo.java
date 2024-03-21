package com.bot.chillburger.repository;


import com.bot.chillburger.entity.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TelegramUserRepo extends JpaRepository<TelegramUser, UUID> {


    TelegramUser findByChatId(Long chatId);
}
