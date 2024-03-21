package com.bot.chillburger.enums;


public enum BotMessage {

    SHARE_YOUR_CONTACT_MSG("Iltimos ma'lumotingizni ulashing", "Please share your contact"),
    SHARE_YOUR_CONTACT_BTN_MSG("Kontakt yuborish", "Share contact"),
    CHOOSE_CITY_MSG("Iltimos, siz yashayotgan shaharni tanlang 👇", "Please, choose your city"),
    SELECT_MAIN_SECTION_BTN_MSG("Bo'limni tanlang:", "choose the section:"),
    MENU_BTN_MSG("Menyu", "Menu"),
    BYD_BTN_MSG("BYD yutib oling", "Win a BYD"),
    ORDERS_BTN_MSG("Mening buyurtmalarim", "My orders"),
    BRANCH_BTN_MSG("Filiallarimiz", "Our branches"),
    CONTACT_BTN_MSG("Qayta aloqa", "Contact"),
    SETTINGS_BTN_MSG("Sozlamalar", "Settings"),
    BYD_SECTION_MSG("50-filial ochilishi munosabati bilan o'tkazilgan BYD Song Champion" +
            " aksiyasi yakunlandi, bosh sovrin g'olibi 19-mart kuni Oybek filialida aniqlanadi.", "" +
            "The BYD Song Champion promotion held in connection with the opening of the 50th " +
            "branch has ended, the winner of the main prize will be determined on March 19 at " +
            "the Oybek branch."),
    BYD_SECTION_INDETAILS_MSG("Batafsil ma'lumot", "learn more"),
    BYD_SECTION_OFFER_MSG("Ommaviy oferta", "Public offer"),
    BYD_SECTION_CONTACT_MSG("Savol berish", "Ask a question"),
    BYD_SECTION_CHANCE_MSG("Mening imkoniyatlarim", "My options"),
    BYD_SECTION_CHANCE_TEXT_MSG("Hozirda sizda shaxsiy ishtirok kodlaringiz yo'q, 70 000 so'mdan buyurtma qiling, kodlarni qo'lga kiriting va BYD Song Champion elektromobili hamda 49 sovrindan birini yutib olish imkoniyatiga ega bo'ling.",
            "You currently do not have personal participation codes, order from 70,000 soums, get the codes and have a chance to win a BYD Song Champion electric car and one of 49 prizes."),
    BYD_SECTION_CONTACT_ADMIN_MSG("BYD Song Champion EV " +
                                          "tanlovi bo'yicha savollaringiz yoki shikoyatingizni " +
                                          "shu yerda qoldirishingiz mumkin, biz siz bilan albatta bog'lanamiz\uD83D\uDC47","BYD Song Champion EV " +
                                          "your questions or complaints about the contest " +
                                          "you can leave it here, we will definitely contact you\uD83D\uDC47");


    private String textUzb;
    private String textEng;

    BotMessage(String textUzb, String textEng) {
        this.textUzb = textUzb;
        this.textEng = textEng;
    }

    public String getTextUzb() {
        return textUzb;
    }

    public String getTextEng() {
        return textEng;
    }
}
