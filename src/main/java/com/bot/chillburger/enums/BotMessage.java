package com.bot.chillburger.enums;

import lombok.Getter;

@Getter
public enum BotMessage {

    SHARE_YOUR_CONTACT_MSG("Telefon raqamingizni quyidagi tarzda \uD83D\uDC47 yuboring yoki kiriting:\n" +
            "+998 ** *** ****", "Отправьте или введите свой номер телефона \uD83D\uDC47  в виде:\n" +
            "+998 ** *** ****"),
    SHARE_YOUR_CONTACT_BTN_MSG("\uD83D\uDCF1 Mening raqamim", "\uD83D\uDCF1 Мой номер"),
    CHOOSE_CITY_MSG("Iltimos, siz yashayotgan shaharni tanlang 👇", "Пожалуйста, выберите город, в котором вы живёте \uD83D\uDC47"),
    SELECT_MAIN_SECTION_BTN_MSG("Bo'limni tanlang:", "Выберите раздел:"),
    MENU_BTN_MSG("\uD83C\uDF55 Menyu", "\uD83C\uDF55 Меню"),
    BYD_BTN_MSG("\uD83C\uDF89 BYD yutib oling", "\uD83C\uDF89 Выиграйте BYD"),
    ORDERS_BTN_MSG("\uD83D\uDCD6 Mening buyurtmalarim", "\uD83D\uDCD6 Мои заказы"),
    CONTACT_BTN_MSG("☎\uFE0F Qayta aloqa", "☎\uFE0F Обратная связь"),
    BYD_SECTION_MSG("50-filial ochilishi munosabati bilan o'tkazilgan BYD Song Champion" +
            " aksiyasi yakunlandi, bosh sovrin g'olibi 19-mart kuni Oybek filialida aniqlanadi.", "" +
            "Акция BYD Song Champion, проведенная в честь открытия 50-го филиала, завершилась, обладатель главного приза будет определен 19 марта в филиале «Ойбек»."),
    BYD_SECTION_INDETAILS_MSG("Batafsil ma'lumot", "Подробнее"),
    BYD_SECTION_OFFER_MSG("Ommaviy oferta", "Публичная оферта"),
    BYD_SECTION_CONTACT_MSG("Savol berish", "Задайте вопрос"),
    BYD_SECTION_CHANCE_MSG("Mening imkoniyatlarim", "Мои возможности"),
    BYD_SECTION_CHANCE_TEXT_MSG("Hozirda sizda shaxsiy ishtirok kodlaringiz yo'q, 70 000 so'mdan buyurtma qiling, kodlarni qo'lga kiriting va BYD Song Champion elektromobili hamda 49 sovrindan birini yutib olish imkoniyatiga ega bo'ling.",
            "На данный момент у вас нет уникальных кодов для участия, оформляйте заказ на сумму от 70 000 сум и получайте уникальные коды, чтобы стать участником розыгрыша BYD Song Champion и других 49 призов."),
    BYD_SECTION_CONTACT_ADMIN_MSG("BYD Song Champion EV " +
                                          "tanlovi bo'yicha savollaringiz yoki shikoyatingizni " +
                                          "shu yerda qoldirishingiz mumkin, biz siz bilan albatta bog'lanamiz\uD83D\uDC47","Вы можете задать свои вопросы по розыгрышу BYD Song Champion EV или оставить жалобу и мы обязательно свяжемся с Вами\uD83D\uDC47"),
    MAIN_MENU_MSG("Buyurtmangizni " +
            "mustaqil olib keting \uD83D\uDE4B\u200D♂\uFE0F yoki yetkazish" +
            " xizmatini tanlang \uD83D\uDE99","Заберите свой заказ самостоятельно \uD83D\uDE4B\u200D♂\uFE0F или выберите доставку \uD83D\uDE99"),
    MAIN_MENU_DELIVERY_BTN_MSG("\uD83D\uDE99 Yetkazish","\uD83D\uDE99 Доставка"),
    MAIN_MENU_DELIVERY_MSG("Buyurtmangizni qaerga yetkazish kerak? " +
            "Lokatsiyani yoki saqlangan manzilni jo‘nating va biz sizga eng" +
            " yaqin joylashgan filialni aniqlaymiz \uD83C\uDF55 \uD83D\uDCCD",
            "Куда нужно доставить Ваш заказ? Отправьте свою локацию или сохраненный адрес и мы определим ближайший к вам филиал \uD83C\uDF55 \uD83D\uDCCD"),
    MAIN_MENU_TAKE_AWAY_BTN_MSG("\uD83C\uDFC3 Olib ketish","\uD83C\uDFC3 Самовывоз"),
    BACK_BTN_MSG("⬅\uFE0F Orqaga","⬅\uFE0F Назад"),
    FIND_NEAR_BRANCH_BTN_MSG("\uD83D\uDCCD Eng yaqin filialni aniqlash","\uD83D\uDCCD Определить блежайший филиал"),
    NOT_FOUND_BRANCH_MSG("❌ Filial texnik sabablarga koʻra ishlamayapti yoki manzilingiz yetkazib berish hududida emas \uD83E\uDD72 \n" +
            "Uzr soʻraymiz.","❌ Филиал не работает по техническим причинам или ваш адрес недействителен."),
    MAIN_MENU_TAKE_AWAY_MSG("Buyurtmani qaerdan olib ketish siz uchun qulay? Lokatsiyani yoki saqlangan manzilni jo‘nating va biz" +
            " sizga eng yaqin joylashgan filialni" +
            " aniqlaymiz \uD83C\uDF55 \uD83D\uDCCD","Откуда Вам удобнее забрать заказ? Отправьте свою локацию или сохраненный адрес и мы определим ближайший к вам филиал \uD83C\uDF55 \uD83D\uDCCD"),
    BELISSIMO_BUKHARA("Belissimo Buxoro","Белиссимо Бухара"),
    INTERACTIVE_MENU_MSG(" sizni ko‘rganimizdan xursandmiz! Bugun nima buyurtma qilasiz? \uD83C\uDF55","мы рады вас видеть! Что ты закажешь сегодня? \uD83C\uDF55"),
    BASKET_BTN_MSG("\uD83D\uDCE5 Savat","\uD83D\uDCE5 Корзина"),
    SELECT_SIZE_AND_MODIFICATOR("Kattaligi va modifikatorni tanlang:","Выберите размер пиццы и модификатор: "),
    ADD_TO_BASKET_BTN_MSG("Savatga qo'shish \uD83D\uDED2","Дoбавит \uD83D\uDED2"),
    ADDED_TO_BASKET_MSG(" mahsuloti savatga muvaffaqiyatli qo‘shildi \uD83D\uDCE5 Yana biror " +
            "nima buyurtma qilamizmi?"," успешно добавлен в корзину \uD83D\uDCE5 Закажем что-нибудь еще?"),
    CONTINUE_BTN_MSG("Davom etish ➡\uFE0F","➡\uFE0F Продолжить"),
    CLEAN_BTN_MSG("\uD83D\uDD04 Tozalash","\uD83D\uDD04  Очистить"),
    TOTAL_AMOUNT_MSG("Umumiy narx: ","Итого: "),
    GO_TO_MENU("\uD83C\uDF55 Menyuga o'tish","\uD83C\uDF55Перейти в меню"),
    CONFIRMED_MSG("Buyurtmangiz qabul qilindi✅","Ваш заказ принят✅"),
    CONTACT_MSG("\uD83D\uDCF2 Yagona call-markaz: 1174 yoki (91) 203-66-66","\uD83D\uDCF2 Единый call-центр: 1174 или (71) 203-66-66"),
    THIN("Yupqa","Тонкий"),
    THICK("Qalin","Толстый"),
    HOT_DOG_BURGER("Hot-dog bort","Хот-дог борт"),
    INTERACTIVE_LOCATION_MSG("<b>\" + \" Bellissimo Buxoro \" + \"</b> \\n\" +\n" +
            "                \"<i>\" + \" \\uD83D\\uDCCD Mustaqillik koʻchasi, 1/1, Buxoro \" + \"</i> \\n\" +\n" +
            "\uD83D\uDD53 11:00 - 04:30 ", "<b>Bellissimo Buxoro</b>\n" +
            "<i>" + "\uD83D\uDCCD улица Мустакиллик, 1/1, Бухара </i> \n" +
            "🕓 11:00 - 04:30"),
    WEBPAGE_MSG("Yangi va qulay menyu orqali buyurtma bering \uD83D\uDC47\uD83D\uDE09","Закажите через новое удобное меню \uD83D\uDC47\uD83D\uDE09"),
    WEBPAGE_BTN_MSG("\uD83E\uDD29 Interactive menyu","\uD83E\uDD29 Интерактивное меню");



    private final String textUzb;
    private final String textRu;

    BotMessage(String textUzb, String textRu) {
        this.textUzb = textUzb;
        this.textRu = textRu;
    }
}
