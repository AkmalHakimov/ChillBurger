package com.bot.chillburger.entity;

import com.bot.chillburger.enums.ProductSize;
import com.bot.chillburger.enums.ProductType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private ProductSize productSize = ProductSize.SMALL;
    @Enumerated(EnumType.STRING)
    private ProductType productType = ProductType.THIN;
    @OneToOne
    private Product product;
    @ManyToOne
    private Order order;
    private Integer amount;
    @ManyToOne
    private TelegramUser telegramUser;
}
