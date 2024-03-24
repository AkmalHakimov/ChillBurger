package com.bot.chillburger.enums;

import lombok.Getter;

@Getter
public enum ProductType {
    THICK("Qalin","Thick"),
    THIN("Yupqa","Thin"),
    HOT_DOG_BORT("Hot-dog bort","Hot-dog bort");

    private String uzLang;
    private String engLang;

    ProductType(String uzLang, String engLang) {
        this.uzLang = uzLang;
        this.engLang = engLang;
    }
}
