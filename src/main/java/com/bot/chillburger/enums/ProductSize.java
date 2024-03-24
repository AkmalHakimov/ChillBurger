package com.bot.chillburger.enums;

import lombok.Getter;

@Getter
public enum ProductSize {
    SMALL("Kichkina","Smaller"),
    MEDIUM("O'rtacha","Medium"),
    HUGE("Katta","Bigger");

    private String uzLang;
    private String engLang;

    ProductSize(String uzLang, String engLang) {
        this.uzLang = uzLang;
        this.engLang = engLang;
    }
}
