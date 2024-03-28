package com.bot.chillburger.enums;

import lombok.Getter;

@Getter
public enum ProductType {
    THICK("Qalin","Thick"),
    THIN("Yupqa","Thin"),
    HOT_DOG_BORT("Hot-dog bort","Hot-dog bort");

    private String uzLang;
    private String ruLang;

    ProductType(String uzLang, String ruLang) {
        this.uzLang = uzLang;
        this.ruLang = ruLang;
    }
}
