package com.bot.chillburger.entity;

import com.bot.chillburger.enums.BotState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "telegram_users")
public class TelegramUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false,unique = true)
    private Long chatId;
    @Enumerated(EnumType.STRING)
    private BotState state;
    private String phone;
    private String selectedLang;
    private Integer contactMessageId;
    private Integer cityMsgId;
    private String selectedCity;
    private Integer mainSecBtnsId;
}
