package com.bot.chillburger.enums;

import lombok.Getter;

@Getter
public enum ProductSize {
    SMALL("Kichkina","Smaller"),
    MEDIUM("O'rtacha","Medium"),
    HUGE("Katta","Bigger");

    private String uzLang;
    private String ruLang;

    ProductSize(String uzLang, String ruLang) {
        this.uzLang = uzLang;
        this.ruLang = ruLang;
    }
}
