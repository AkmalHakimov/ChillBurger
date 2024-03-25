package com.bot.chillburger.enums;

import lombok.Getter;

@Getter
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
                                          "you can leave it here, we will definitely contact you\uD83D\uDC47"),
    MAIN_MENU_MSG("Buyurtmangizni " +
            "mustaqil olib keting \uD83D\uDE4B\u200D♂\uFE0F yoki yetkazish" +
            " xizmatini tanlang \uD83D\uDE99","Pick up" +
            " your order yourself \uD83D\uDE4B\u200D♂\uFE0F or choose a delivery " +
            "service \uD83D\uDE99"),
    MAIN_MENU_DELIVERY_BTN_MSG("Yetkazish","Delivery"),
    MAIN_MENU_DELIVERY_MSG("Buyurtmangizni qaerga yetkazish kerak? " +
            "Lokatsiyani yoki saqlangan manzilni jo‘nating va biz sizga eng" +
            " yaqin joylashgan filialni aniqlaymiz \uD83C\uDF55 \uD83D\uDCCD","" +
            "Where should your order be delivered? Submit your location or saved address and we'll" +
            " find the branch closest to you \uD83C\uDF55 \uD83D\uDCCD"),
    MAIN_MENU_TAKE_AWAY_BTN_MSG("Olib ketish","Take away"),
    BACK_BTN_MSG("Orqaga","Back"),
    FIND_NEAR_BRANCH_BTN_MSG("Eng yaqin filialni aniqlash","Identify the nearest branch"),
    NOT_FOUND_BRANCH_MSG("❌ Filial texnik sabablarga koʻra ishlamayapti yoki manzilingiz yetkazib berish hududida emas \uD83E\uDD72 \n" +
            "Uzr soʻraymiz.","❌ The branch is not working due to technical reasons or your address is not valid\n" +
            "We apologize."),
    MAIN_MENU_TAKE_AWAY_MSG("Buyurtmani qaerdan olib ketish siz uchun qulay? Lokatsiyani yoki saqlangan manzilni jo‘nating va biz" +
            " sizga eng yaqin joylashgan filialni" +
            " aniqlaymiz \uD83C\uDF55 \uD83D\uDCCD","Where is convenient for you to pick up the order? Submit your location or saved address " +
            "and we'll find the branch closest to you \uD83C\uDF55 \uD83D\uDCCD"),
    BELISSIMO_BUKHARA("Belissimo Buxoro","Belissimo Bukhara"),
    INTERACTIVE_MENU_MSG(" sizni ko‘rganimizdan xursandmiz! Bugun nima buyurtma qilasiz? \uD83C\uDF55","" +
            "we are glad to see you! What will you order today? \uD83C\uDF55"),
    BASKET_BTN_MSG("Savat","Basket"),
    SELECT_SIZE_AND_MODIFICATOR("Kattaligi va modifikatorni tanlang:","Choose size and modifier: "),
    ADD_TO_BASKET_BTN_MSG("Savatga qo'shish","Add to basket"),
    ADDED_TO_BASKET_MSG(" mahsuloti savatga muvaffaqiyatli qo‘shildi \uD83D\uDCE5 Yana biror " +
            "nima buyurtma qilamizmi?"," product has been successfully" +
            " added to the cart \uD83D\uDCE5 Would you like to order something else?"),
    CONTINUE_BTN_MSG("Davom etish","Continue"),
    CLEAN_BTN_MSG("Tozalash","Clean"),
    TOTAL_AMOUNT_MSG("Umumiy narx: ","Total: "),
    GO_TO_MENU("Menyuga o'tish","Menu");

    private final String textUzb;
    private final String textEng;

    BotMessage(String textUzb, String textEng) {
        this.textUzb = textUzb;
        this.textEng = textEng;
    }
}
