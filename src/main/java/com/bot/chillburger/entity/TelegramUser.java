package com.bot.chillburger.entity;

import com.bot.chillburger.enums.BotState;
import com.bot.chillburger.enums.ProductSize;
import com.bot.chillburger.enums.ProductType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
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
    private String selectedCity;
    private Integer categoryId;
//    @Column(columnDefinition = "integer default 1")
    private Integer amountCounter = 1;
    private Integer addBasketMsgId;
    @Enumerated(EnumType.STRING)
    private ProductSize currentProductSize;
    @Enumerated(EnumType.STRING)
    private ProductType currentProductType;
    private Integer currentProductId;

    @PrePersist
    public void prePersist() {
        if (amountCounter == null) {
            amountCounter = 1; // Set default value
        }
        if (currentProductSize == null) {
            currentProductSize = ProductSize.SMALL; // Set default value
        }
        if (currentProductType == null) {
            currentProductType = ProductType.THIN; // Set default value
        }
    }
}

