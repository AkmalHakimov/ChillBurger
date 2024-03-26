package com.bot.chillburger.telegramBot;

import com.bot.chillburger.entity.Category;
import com.bot.chillburger.entity.OrderProduct;
import com.bot.chillburger.entity.Product;
import com.bot.chillburger.enums.*;
import com.bot.chillburger.entity.TelegramUser;
import com.bot.chillburger.repository.CategoryRepo;
import com.bot.chillburger.repository.OrderProductRepo;
import com.bot.chillburger.repository.ProductRepo;
import com.bot.chillburger.repository.TelegramUserRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AppBot extends TelegramLongPollingBot {

    TelegramUser telegramUser = null;
    String photoId = "AgACAgIAAxkBAAIC92X_p57041LDAXn2QJTH_9EK1-2tAAL-3DEbTbX5SxbzedSuLC2lAQADAgADcwADNAQ";
    String adminChatId = "1539471133";
    private final TelegramUserRepo telegramUserRepo;
    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;
    private final OrderProductRepo orderProductRepo;


    @SneakyThrows
    @Autowired
    public AppBot(TelegramBotsApi api, TelegramUserRepo telegramUserRepo, CategoryRepo categoryRepo, ProductRepo productRepo, OrderProductRepo orderProductRepo) {
        this.telegramUserRepo = telegramUserRepo;
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
        this.orderProductRepo = orderProductRepo;
        api.registerBot(this);
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            Long chatId = message.getChatId();
            boolean checkIfUserAvailableInDb = telegramUserRepo.findByChatId(chatId) == null;
            telegramUser = findUserByChatId(chatId);
            if (message.hasText()) {
                String text = message.getText();
                if (text.equals("/start")) {
                    if(checkIfUserAvailableInDb){
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(telegramUser.getChatId());
                        sendMessage.setText("Выберите язык:");
                        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                        List<InlineKeyboardButton> row = new ArrayList<>();
                        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
                        InlineKeyboardButton button1 = new InlineKeyboardButton();
                        button1.setText("Uzbek");
                        button1.setCallbackData(BotCallBackData.SET_LANG_UZB.toString());
                        row.add(button1);
                        InlineKeyboardButton button2 = new InlineKeyboardButton();
                        button2.setText("English");
                        button2.setCallbackData(BotCallBackData.SET_LANG_ENG.toString());
                        row.add(button2);
                        rows.add(row);
                        inlineKeyboardMarkup.setKeyboard(rows);
                        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
                        telegramUser.setState(BotState.CHOOSE_LANG);
                        saveTelegramUserToDb();
                        execute(sendMessage);
                    }else {
                        menuButtons(chatId);
                    }

                } else if (telegramUser.getState().equals(BotState.SELECT_CITY)) {
                    telegramUser.setSelectedCity(text);
                    menuButtons(chatId);
                } else if (telegramUser.getState().equals(BotState.MAIN_SECTION)) {
                    if (text.equals(ShowBotMessage(BotMessage.BYD_BTN_MSG))) {
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setText(ShowBotMessage(BotMessage.BYD_SECTION_MSG));
                        sendMessage.setChatId(chatId);
                        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                        List<InlineKeyboardButton> row = new ArrayList<>();
                        List<InlineKeyboardButton> row1 = new ArrayList<>();
                        List<InlineKeyboardButton> row2 = new ArrayList<>();
                        List<InlineKeyboardButton> row3 = new ArrayList<>();
                        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setUrl("https://bellissimo.uz/");
                        button.setText(ShowBotMessage(BotMessage.BYD_SECTION_INDETAILS_MSG));
                        row.add(button);
                        InlineKeyboardButton button1 = new InlineKeyboardButton();
                        button1.setUrl("https://bellissimo.fra1.digitaloceanspaces.com/promo-byd-offerta/offerta_uz.pdf");
                        button1.setText(ShowBotMessage(BotMessage.BYD_SECTION_OFFER_MSG));
                        row1.add(button1);
                        InlineKeyboardButton button2 = new InlineKeyboardButton();
                        button2.setCallbackData(BotCallBackData.MAIN_SECTION_CONTACT.toString());
                        button2.setText(ShowBotMessage(BotMessage.BYD_SECTION_CONTACT_MSG));
                        row2.add(button2);
                        InlineKeyboardButton button3 = new InlineKeyboardButton();
                        button3.setCallbackData(BotCallBackData.MAIN_SECTION_CHANCE.toString());
                        button3.setText(ShowBotMessage(BotMessage.BYD_SECTION_CHANCE_MSG));
                        row3.add(button3);
                        rows.add(row);
                        rows.add(row1);
                        rows.add(row2);
                        rows.add(row3);
                        inlineKeyboardMarkup.setKeyboard(rows);
                        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
                        execute(sendMessage);
                        telegramUser.setState(BotState.MAIN_SECTION);
                        saveTelegramUserToDb();
                        sendSelectSectionMsg(chatId);
                    } else if (text.equals(ShowBotMessage(BotMessage.MENU_BTN_MSG))) {
                        menuDeliverySection(chatId);
                        telegramUser.setState(BotState.MENU_SELECT_DELIVERY_TYPE);
                        saveTelegramUserToDb();
                    }
                } else if (telegramUser.getState().equals(BotState.CONTACT_ADMIN)) {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(adminChatId);
                    sendMessage.setText(text);
                    execute(sendMessage);
                    sendSelectSectionMsg(chatId);
                } else if (telegramUser.getState().equals(BotState.MENU_SELECT_DELIVERY_TYPE)) {
                    if (checkStateForBackBtn(text)) {
                        menuButtons(chatId);
                    } else if (text.equals(ShowBotMessage(BotMessage.MAIN_MENU_DELIVERY_BTN_MSG))) {
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(chatId);
                        sendMessage.setText(ShowBotMessage(BotMessage.MAIN_MENU_DELIVERY_MSG));
                        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                        List<KeyboardRow> rows = new ArrayList<>();
                        KeyboardRow row = new KeyboardRow();
                        KeyboardRow row1 = new KeyboardRow();
                        KeyboardButton button = new KeyboardButton();
                        button.setText(ShowBotMessage(BotMessage.FIND_NEAR_BRANCH_BTN_MSG));
                        button.setRequestLocation(true);
                        row.add(button);
                        KeyboardButton button1 = new KeyboardButton();
                        button1.setText(ShowBotMessage(BotMessage.BACK_BTN_MSG));
                        row1.add(button1);
                        rows.add(row);
                        rows.add(row1);
                        replyKeyboardMarkup.setKeyboard(rows);
                        replyKeyboardMarkup.setSelective(true);
                        replyKeyboardMarkup.setResizeKeyboard(true);
                        sendMessage.setReplyMarkup(replyKeyboardMarkup);
                        Message execute = execute(sendMessage);
                        telegramUser.setState(BotState.FIND_CLOSE_BRANCH);
                        saveTelegramUserToDb();
                    } else if (text.equals(ShowBotMessage(BotMessage.MAIN_MENU_TAKE_AWAY_BTN_MSG))) {
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(chatId);
                        sendMessage.setText(ShowBotMessage(BotMessage.MAIN_MENU_TAKE_AWAY_MSG));
                        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                        List<KeyboardRow> rows = new ArrayList<>();
                        KeyboardRow row = new KeyboardRow();
                        KeyboardButton button = new KeyboardButton();
                        button.setText(ShowBotMessage(BotMessage.BACK_BTN_MSG));
                        row.add(button);
                        KeyboardButton button1 = new KeyboardButton();
                        button1.setText(ShowBotMessage(BotMessage.FIND_NEAR_BRANCH_BTN_MSG));
                        button1.setRequestLocation(true);
                        row.add(button1);
                        KeyboardRow row1 = new KeyboardRow();
                        KeyboardButton button2 = new KeyboardButton();
                        button2.setText(ShowBotMessage(BotMessage.BELISSIMO_BUKHARA));
                        row1.add(button2);
                        rows.add(row);
                        rows.add(row1);
                        replyKeyboardMarkup.setKeyboard(rows);
                        replyKeyboardMarkup.setSelective(true);
                        replyKeyboardMarkup.setResizeKeyboard(true);
                        sendMessage.setReplyMarkup(replyKeyboardMarkup);
                        execute(sendMessage);
                        telegramUser.setState(BotState.TAKE_AWAY_SELECT_TYPE);
                        saveTelegramUserToDb();
                    }
                } else if (telegramUser.getState().equals(BotState.FIND_CLOSE_BRANCH) && checkStateForBackBtn(text)) {
                    menuDeliverySection(chatId);
                    telegramUser.setState(BotState.MENU_SELECT_DELIVERY_TYPE);
                    saveTelegramUserToDb();
                } else if (telegramUser.getState().equals(BotState.TAKE_AWAY_SELECT_TYPE)) {
                    if (checkStateForBackBtn(text)) {
                        menuDeliverySection(chatId);
                        telegramUser.setState(BotState.MENU_SELECT_DELIVERY_TYPE);
                        saveTelegramUserToDb();
                    } else if (text.equals(ShowBotMessage(BotMessage.FIND_NEAR_BRANCH_BTN_MSG))) {
                        menuDeliverySection(chatId);
                        telegramUser.setState(BotState.MENU_SELECT_DELIVERY_TYPE);
                        saveTelegramUserToDb();
                    } else if (text.equals(ShowBotMessage(BotMessage.BELISSIMO_BUKHARA))) {
                        interactiveMenuSection(chatId, message);
                    }
                } else if (telegramUser.getState().equals(BotState.SELECT_INTERACTIVE_MENU)) {
                    Category category = categoryRepo.findByEngNameContainingOrUzNameContaining(text, text);
                    if (checkStateForBackBtn(text)) {
                        menuDeliverySection(chatId);
                        telegramUser.setState(BotState.MENU_SELECT_DELIVERY_TYPE);
                        saveTelegramUserToDb();
                    } else if (text.equals(ShowBotMessage(BotMessage.BASKET_BTN_MSG) + "(" + orderProductRepo.countAll(findUserByChatId(chatId).getId()) + ")")) {
                        ShowBasket(chatId);
                    } else if (category != null) {
                        createProductSectionByCategory(category, chatId);
                    }
                } else if (telegramUser.getState().equals(BotState.ADD_PRODUCT_MENU)) {
                    Product product = productRepo.findByEngNameContainingOrUzNameContaining(text, text);
                    if (checkStateForBackBtn(text)) {
                        interactiveMenuSection(chatId, message);
                        telegramUser.setState(BotState.SELECT_INTERACTIVE_MENU);
                        saveTelegramUserToDb();
                    } else if (text.equals(ShowBotMessage(BotMessage.BASKET_BTN_MSG) + "(" + orderProductRepo.countAll(findUserByChatId(chatId).getId()) + ")")) {
                        ShowBasket(chatId);
                    } else if (product != null) {
                        addBasketSection(product, chatId);
                    }
                }
            } else if (message.hasContact() && telegramUser.getState().equals(BotState.SHARE_CONTACT)) {
                Contact contact = message.getContact();
                String x = contact.getPhoneNumber();
                String phoneNumber = x.startsWith("+998") ? x : "+998" + x;
                telegramUser.setPhone(phoneNumber);
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText(ShowBotMessage(BotMessage.CHOOSE_CITY_MSG));
                ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                List<KeyboardRow> rows = new ArrayList<>();
                KeyboardRow row1 = new KeyboardRow();
                KeyboardRow row2 = new KeyboardRow();
                KeyboardRow row3 = new KeyboardRow();
                KeyboardRow row4 = new KeyboardRow();
                KeyboardRow row5 = new KeyboardRow();
                KeyboardRow row6 = new KeyboardRow();
                KeyboardButton button1 = new KeyboardButton();
                button1.setText("Toshkent");
                row1.add(button1);
                KeyboardButton button2 = new KeyboardButton();
                button2.setText("Samarqand");
                row1.add(button2);
                KeyboardButton button3 = new KeyboardButton();
                button3.setText("Andijon");
                row2.add(button3);
                KeyboardButton button4 = new KeyboardButton();
                button4.setText("Qo'qon");
                row2.add(button4);
                KeyboardButton button5 = new KeyboardButton();
                button5.setText("Farg'ona");
                row3.add(button5);
                KeyboardButton button6 = new KeyboardButton();
                button6.setText("Chirchiq");
                row3.add(button6);
                KeyboardButton button7 = new KeyboardButton();
                button7.setText("Namangan");
                row4.add(button7);
                KeyboardButton button8 = new KeyboardButton();
                button8.setText("Buxoro");
                row4.add(button8);
                KeyboardButton button9 = new KeyboardButton();
                button9.setText("Toshkent");
                row5.add(button9);
                KeyboardButton button10 = new KeyboardButton();
                button10.setText("Samarqand");
                row5.add(button10);
                KeyboardButton button11 = new KeyboardButton();
                button11.setText("Angren");
                row6.add(button11);
                rows.add(row1);
                rows.add(row2);
                rows.add(row3);
                rows.add(row4);
                rows.add(row5);
                rows.add(row6);
                replyKeyboardMarkup.setKeyboard(rows);
                replyKeyboardMarkup.setSelective(true);
                replyKeyboardMarkup.setResizeKeyboard(true);
                sendMessage.setReplyMarkup(replyKeyboardMarkup);
                Message execute = execute(sendMessage);
                telegramUser.setState(BotState.SELECT_CITY);
                saveTelegramUserToDb();
            } else if (message.hasLocation() && (telegramUser.getState().equals(BotState.FIND_CLOSE_BRANCH) || telegramUser.getState().equals(BotState.TAKE_AWAY_SELECT_TYPE))) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setText(ShowBotMessage(BotMessage.NOT_FOUND_BRANCH_MSG));
                sendMessage.setChatId(chatId);
                execute(sendMessage);
                telegramUser.setState(BotState.FIND_CLOSE_BRANCH);
                saveTelegramUserToDb();
            } else if (message.hasPhoto()) {
                PhotoSize first = message.getPhoto().getFirst();
                for (PhotoSize photoSize : message.getPhoto()) {
                    System.out.println(photoSize.getFileSize());
                    Integer fileSize = photoSize.getFileSize();
                }
                System.out.println(first.getFileId());
            }
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            Long chatId = callbackQuery.getMessage().getChatId();
            telegramUser = findUserByChatId(chatId);
            String data = callbackQuery.getData();
            if (telegramUser.getState().equals(BotState.CHOOSE_LANG)) {
                telegramUser.setSelectedLang(data);
                SendMessage sendMessage = new SendMessage();
                sendMessage.setText(ShowBotMessage(BotMessage.SHARE_YOUR_CONTACT_MSG));
                sendMessage.setChatId(chatId);
                sendMessage.setReplyMarkup(generateContactBtn());
                Message execute = execute(sendMessage);
                telegramUser.setState(BotState.SHARE_CONTACT);
                saveTelegramUserToDb();
            } else if (telegramUser.getState().equals(BotState.MAIN_SECTION)) {
                if (data.equals(BotCallBackData.MAIN_SECTION_CONTACT.toString())) {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(chatId);
                    sendMessage.setText(ShowBotMessage(BotMessage.BYD_SECTION_CONTACT_ADMIN_MSG));
                    execute(sendMessage);
                    telegramUser.setState(BotState.CONTACT_ADMIN);
                    saveTelegramUserToDb();
                } else {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(chatId);
                    sendMessage.setText(ShowBotMessage(BotMessage.BYD_SECTION_CHANCE_TEXT_MSG));
                    execute(sendMessage);
                    sendSelectSectionMsg(chatId);
                }
            } else if (telegramUser.getState().equals(BotState.ADD_TO_BASKET)) {
                if (data.equals(BotCallBackData.MEDIUM.toString())) {
                    telegramUser.setCurrentProductSize(ProductSize.MEDIUM);
                    saveTelegramUserToDb();
                    editCaption(chatId);
                    editMarkup(chatId);
                } else if (data.equals(BotCallBackData.SMALL.toString())) {
                    telegramUser.setCurrentProductSize(ProductSize.SMALL);
                    saveTelegramUserToDb();
                    editCaption(chatId);
                    editMarkup(chatId);
                } else if (data.equals(BotCallBackData.HUGE.toString())) {
                    telegramUser.setCurrentProductSize(ProductSize.HUGE);
                    saveTelegramUserToDb();
                    editCaption(chatId);
                    editMarkup(chatId);
                } else if (data.equals(BotCallBackData.THIN.toString())) {
                    telegramUser.setCurrentProductType(ProductType.THIN);
                    saveTelegramUserToDb();
                    editCaption(chatId);
                    editMarkup(chatId);
                } else if (data.equals(BotCallBackData.THICK.toString())) {
                    telegramUser.setCurrentProductType(ProductType.THICK);
                    saveTelegramUserToDb();
                    editCaption(chatId);
                    editMarkup(chatId);
                } else if (data.equals(BotCallBackData.HOT_DOG_BORT.toString())) {
                    telegramUser.setCurrentProductType(ProductType.HOT_DOG_BORT);
                    saveTelegramUserToDb();
                    editCaption(chatId);
                    editMarkup(chatId);
                } else if (data.equals(BotCallBackData.PLUS.toString())) {
                    telegramUser.setAmountCounter(telegramUser.getAmountCounter() + 1);
                    saveTelegramUserToDb();
                    editCaption(chatId);
                    editMarkup(chatId);
                } else if (data.equals(BotCallBackData.MINUS.toString()) && telegramUser.getAmountCounter() > 1) {
                    telegramUser.setAmountCounter(telegramUser.getAmountCounter() - 1);
                    saveTelegramUserToDb();
                    editCaption(chatId);
                    editMarkup(chatId);
                } else if (data.equals(BotCallBackData.ADD_TO_BASKET.toString())) {
                    deleteProductMsg(chatId);
                    if (orderProductRepo.getOrderProductByProductId(telegramUser.getCurrentProductId()) != null) {
                        Integer x = telegramUser.getAmountCounter() + orderProductRepo.getOrderProductByProductId(telegramUser.getCurrentProductId()).getAmount();
                        orderProductRepo.save(OrderProduct.builder()
                                .id(orderProductRepo.getOrderProductByProductId(telegramUser.getCurrentProductId()).getId())
                                .amount(x)
                                .productType(telegramUser.getCurrentProductType())
                                .productSize(telegramUser.getCurrentProductSize())
                                .product(productRepo.findById(telegramUser.getCurrentProductId()).orElseThrow())
                                .telegramUser(findUserByChatId(chatId))
                                .build());
                    } else orderProductRepo.save(OrderProduct.builder()
                            .amount(telegramUser.getAmountCounter())
                            .productType(telegramUser.getCurrentProductType())
                            .productSize(telegramUser.getCurrentProductSize())
                            .telegramUser(findUserByChatId(chatId))
                            .product(productRepo.findById(telegramUser.getCurrentProductId()).orElseThrow())
                            .build());
                    SendMessage sendMessage = new SendMessage(chatId.toString(), productRepo.findById(telegramUser.getCurrentProductId()).orElseThrow().getEngName() + ShowBotMessage(BotMessage.ADDED_TO_BASKET_MSG));
                    execute(sendMessage);
                    createProductSectionByCategory(categoryRepo.findById(telegramUser.getCategoryId()).orElseThrow(), chatId);
                    telegramUser.setAmountCounter(1);
                    telegramUser.setCurrentProductSize(ProductSize.SMALL);
                    telegramUser.setCurrentProductType(ProductType.THIN);
                    saveTelegramUserToDb();
                }else if(data.equals(BotCallBackData.BACK_DATA.toString())){
                    deleteProductMsg(chatId);
                    createProductSectionByCategory(categoryRepo.findById(telegramUser.getCategoryId()).orElseThrow(),chatId);
                    telegramUser.setAmountCounter(1);
                    telegramUser.setCurrentProductSize(ProductSize.SMALL);
                    telegramUser.setCurrentProductType(ProductType.THIN);
                    saveTelegramUserToDb();
                }else if(telegramUser.getState().equals(BotState.SHOW_BASKET_STATE)){
                    if(data.equals(ShowBotMessage(BotMessage.BACK_BTN_MSG))){

                    }
                }
            }
        }
    }

    private void ShowBasket(Long chatId) throws TelegramApiException {
        if(orderProductRepo.countAll(telegramUser.getId())==0){
            SendMessage sendMessage = new SendMessage(chatId.toString(),"Your Basket is Empty!");
            execute(sendMessage);
            menuButtons(chatId);
            return;
        }
        SendMessage sendMessage = new SendMessage(chatId.toString(),ShowBotMessage(BotMessage.BASKET_BTN_MSG) + "(" + orderProductRepo.countAll(findUserByChatId(chatId).getId()) + ")");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardButton button = new KeyboardButton();
        button.setText(ShowBotMessage(BotMessage.BACK_BTN_MSG));
        KeyboardButton button1 = new KeyboardButton();
        button1.setText(ShowBotMessage(BotMessage.CONTINUE_BTN_MSG));
        row.add(button);
        row.add(button1);

        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton button2 = new KeyboardButton();
        button2.setText(ShowBotMessage(BotMessage.CLEAN_BTN_MSG));
        row1.add(button2);

        rows.add(row);
        rows.add(row1);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setKeyboard(rows);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        execute(sendMessage);

        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setChatId(chatId);
        sendMessage1.setText(Objects.requireNonNull(returnShowBasketMsgAsString(chatId)));
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("change");
        button3.setCallbackData(BotCallBackData.EDIT_ORDER.name());
        row2.add(button3);
        rows1.add(row2);
        inlineKeyboardMarkup.setKeyboard(rows1);
        sendMessage1.setReplyMarkup(inlineKeyboardMarkup);
        sendMessage1.setParseMode(ParseMode.HTML);
        execute(sendMessage1);

        SendMessage sendMessage2 = new SendMessage(chatId.toString(), "<b>" + ShowBotMessage(BotMessage.TOTAL_AMOUNT_MSG) + "</b>" +  calculateTotalBalance().toString()  +" so'm");
        InlineKeyboardMarkup inlineKeyboardMarkup1 = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows2 = new ArrayList<>();
        List<InlineKeyboardButton> row3 = new ArrayList<>();
        InlineKeyboardButton button4 = new InlineKeyboardButton();
        button4.setText(ShowBotMessage(BotMessage.GO_TO_MENU));
        button4.setCallbackData(BotCallBackData.GO_TO_MENU.name());
        row3.add(button4);
        rows2.add(row3);
        inlineKeyboardMarkup1.setKeyboard(rows2);
        sendMessage2.setReplyMarkup(inlineKeyboardMarkup1);
        sendMessage2.setParseMode(ParseMode.HTML);
        execute(sendMessage2);
        telegramUser.setState(BotState.SHOW_BASKET_STATE);
        saveTelegramUserToDb();
    }

    private Integer calculateTotalBalance() {
        List<OrderProduct> all = orderProductRepo.findAll();
        int s = 0;
        for (OrderProduct orderProduct : all) {
            s += orderProduct.getAmount()*orderProduct.getProduct().getPrice();
        }
        return s;
    }

    private String returnShowBasketMsgAsString(Long chatId) {
        List<OrderProduct> orderIdNullOrdersItems = orderProductRepo.getOrderIdNullOrdersItems(findUserByChatId(chatId).getId());
        String s = "";
        for (OrderProduct orderProduct : orderIdNullOrdersItems) {
            s += + orderProduct.getAmount() + " <b>x</b> " + orderProduct.getProduct().getEngName() + " " + orderProduct.getProductSize() +"\n";
        }

        return s;
    }

    private void deleteProductMsg(Long chatId) throws TelegramApiException {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setMessageId(telegramUser.getAddBasketMsgId());
        deleteMessage.setChatId(chatId);
        execute(deleteMessage);
    }

    private void editMarkup(Long chatId) throws TelegramApiException {
        EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
        editMessageReplyMarkup.setChatId(chatId);
        editMessageReplyMarkup.setMessageId(telegramUser.getAddBasketMsgId());
        editMessageReplyMarkup.setReplyMarkup(getAddBasketInlineKeyboard(chatId));
        execute(editMessageReplyMarkup);
    }

    private void editCaption(Long chatId) throws TelegramApiException {
        EditMessageCaption editMessageCaption = new EditMessageCaption();
        editMessageCaption.setMessageId(telegramUser.getAddBasketMsgId());
        editMessageCaption.setChatId(chatId);
        editMessageCaption.setCaption(getCaption(productRepo.findById(telegramUser.getCurrentProductId()).orElseThrow()));
        execute(editMessageCaption);
    }

    private void addBasketSection(Product product, Long chatId) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage(chatId.toString(), ShowBotMessage(BotMessage.SELECT_SIZE_AND_MODIFICATOR));
        ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
        replyKeyboardRemove.setRemoveKeyboard(true);
        sendMessage.setReplyMarkup(replyKeyboardRemove);
        execute(sendMessage);


        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(new InputFile(product.getPhotoId()));
        sendPhoto.setCaption(getCaption(product));
//        sendPhoto.setCaption("123");
        InlineKeyboardMarkup inlineKeyboardMarkup = getAddBasketInlineKeyboard(chatId);
        sendPhoto.setReplyMarkup(inlineKeyboardMarkup);

        Message execute = execute(sendPhoto);
        telegramUser.setState(BotState.ADD_TO_BASKET);
        telegramUser.setAddBasketMsgId(execute.getMessageId());
        telegramUser.setCurrentProductId(product.getId());
        saveTelegramUserToDb();
    }

    private InlineKeyboardMarkup getAddBasketInlineKeyboard(Long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("маленькая");
        button.setCallbackData(BotCallBackData.SMALL.toString());
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("средняя");
        button1.setCallbackData(BotCallBackData.MEDIUM.name());
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("большая");
        button2.setCallbackData(BotCallBackData.HUGE.toString());
        row.add(button);
        row.add(button1);
        row.add(button2);

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("Yupqa");
        button3.setCallbackData(BotCallBackData.THIN.toString());
        InlineKeyboardButton button4 = new InlineKeyboardButton();
        button4.setText("qalin");
        button4.setCallbackData(BotCallBackData.THICK.toString());
        InlineKeyboardButton button5 = new InlineKeyboardButton();
        button5.setText("Hot-dog bort");
        button5.setCallbackData(BotCallBackData.HOT_DOG_BORT.toString());
        row1.add(button3);
        row1.add(button4);
        row1.add(button5);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        InlineKeyboardButton button6 = new InlineKeyboardButton();
        button6.setText("-");
        button6.setCallbackData(BotCallBackData.MINUS.toString());
        InlineKeyboardButton button7 = new InlineKeyboardButton();
        button7.setText(findUserByChatId(chatId).getAmountCounter().toString());
        button7.setCallbackData("123");
        InlineKeyboardButton button8 = new InlineKeyboardButton();
        button8.setText("+");
        button8.setCallbackData(BotCallBackData.PLUS.toString());
        row2.add(button6);
        row2.add(button7);
        row2.add(button8);

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        InlineKeyboardButton button9 = new InlineKeyboardButton();
        button9.setText(ShowBotMessage(BotMessage.ADD_TO_BASKET_BTN_MSG));
        button9.setCallbackData(BotCallBackData.ADD_TO_BASKET.name());

        row3.add(button9);

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        InlineKeyboardButton button10 = new InlineKeyboardButton();
        button10.setText(ShowBotMessage(BotMessage.BACK_BTN_MSG));
        button10.setCallbackData(BotCallBackData.BACK_DATA.name());

        row4.add(button10);

        rows.add(row);
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);
        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }

    private String getCaption(Product product) {
        return product.getUzName() + ": " + telegramUser.getCurrentProductSize().name() + "\n" +
                "Hamir turi: " + telegramUser.getCurrentProductType().name() + "\n" +
                (product.getDescription() != null ? product.getDescription() : " ") + "\n" +
                "Narx: " + calculateBalance(product.getPrice()) + " so'm";
    }

    private Integer calculateBalance(Integer price) {
        return telegramUser.getAmountCounter() * price;
    }

    private boolean checkStateForBackBtn(String text) {
        return text.equals(ShowBotMessage(BotMessage.BACK_BTN_MSG));
    }

    private void createProductSectionByCategory(Category category, Long chatId) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(telegramUser.getSelectedLang().equals(BotCallBackData.SET_LANG_UZB.toString()) ? category.getUzName() + " ni tanlang" : "choose the " + category.getEngName());

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardButton button = new KeyboardButton();
        KeyboardButton button1 = new KeyboardButton();
        button.setText(ShowBotMessage(BotMessage.BACK_BTN_MSG));
        button1.setText(ShowBotMessage(BotMessage.BASKET_BTN_MSG) + "(" + orderProductRepo.countAll(findUserByChatId(chatId).getId()) + ")");
        row.add(button);
        row.add(button1);
        rows.add(row);

        List<Product> categoryProducts = productRepo.findAllByCategoryId(category.getId());
        double rowCount = Math.ceil((double) categoryProducts.toArray().length / 2);
        for (int i = 0; i < rowCount; i++) {
            KeyboardRow row1 = new KeyboardRow();
            rows.add(row1);
        }

        int currentRow = 1;
        int count = 0;

        for (Product products : categoryProducts) {
            if (count > 1) {
                count = 0;
                currentRow++;
            }
            KeyboardButton button2 = new KeyboardButton();
            button2.setText(defineItsLangByProduct(products));
            KeyboardRow row1 = rows.get(currentRow);
            row1.add(button2);
            count++;
        }
        replyKeyboardMarkup.setKeyboard(rows);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        execute(sendMessage);
        telegramUser.setState(BotState.ADD_PRODUCT_MENU);
        telegramUser.setCategoryId(category.getId());
        saveTelegramUserToDb();
    }

    private void interactiveMenuSection(Long chatId, Message message) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        String fullName = message.getFrom().getFirstName();
        sendMessage.setText(fullName + " " + ShowBotMessage(BotMessage.INTERACTIVE_MENU_MSG));
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardButton button = new KeyboardButton();
        KeyboardButton button1 = new KeyboardButton();
        button.setText(ShowBotMessage(BotMessage.BACK_BTN_MSG));
        button1.setText(ShowBotMessage(BotMessage.BASKET_BTN_MSG) + "(" + orderProductRepo.countAll(findUserByChatId(chatId).getId()) + ")");
        row.add(button);
        row.add(button1);
        rows.add(row);

        List<Category> categories = categoryRepo.findAll();
        double rowCount = Math.ceil((double) categories.toArray().length / 2);
        for (int i = 0; i < rowCount; i++) {
            KeyboardRow row1 = new KeyboardRow();
            rows.add(row1);
        }

        int currentRow = 1;
        int count = 0;

        for (Category category : categories) {
            if (count > 1) {
                count = 0;
                currentRow++;
            }
            KeyboardButton button2 = new KeyboardButton();
            button2.setText(defineItsLangByCategory(category));
            KeyboardRow row1 = rows.get(currentRow);
            row1.add(button2);
            count++;
        }
        replyKeyboardMarkup.setKeyboard(rows);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        execute(sendMessage);
        telegramUser.setState(BotState.SELECT_INTERACTIVE_MENU);
        saveTelegramUserToDb();
    }

    private String defineItsLangByCategory(Category category) {
        return telegramUser.getSelectedLang().equals(BotCallBackData.SET_LANG_UZB.toString()) ? category.getUzName() : category.getEngName();
    }

    private String defineItsLangByProduct(Product product) {
        return telegramUser.getSelectedLang().equals(BotCallBackData.SET_LANG_UZB.toString()) ? product.getUzName() : product.getEngName();
    }

    private void menuDeliverySection(Long chatId) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(ShowBotMessage(BotMessage.MAIN_MENU_MSG));
        sendMessage.setChatId(chatId);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardButton button = new KeyboardButton();
        button.setText(ShowBotMessage(BotMessage.MAIN_MENU_DELIVERY_BTN_MSG));
        KeyboardButton button1 = new KeyboardButton();
        button1.setText(ShowBotMessage(BotMessage.MAIN_MENU_TAKE_AWAY_BTN_MSG));
        row.add(button);
        row.add(button1);
        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton button2 = new KeyboardButton();
        button2.setText(ShowBotMessage(BotMessage.BACK_BTN_MSG));
        row1.add(button2);
        rows.add(row);
        rows.add(row1);
        replyKeyboardMarkup.setKeyboard(rows);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        Message execute = execute(sendMessage);
    }

    private void sendSelectSectionMsg(Long chatId) throws TelegramApiException {
        SendMessage sendMessage1 = new SendMessage();
        sendMessage1.setChatId(chatId);
        sendMessage1.setText(ShowBotMessage(BotMessage.SELECT_MAIN_SECTION_BTN_MSG));
        execute(sendMessage1);
        telegramUser.setState(BotState.MAIN_SECTION);
        saveTelegramUserToDb();
    }

    private void menuButtons(Long chatId) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(ShowBotMessage(BotMessage.SELECT_MAIN_SECTION_BTN_MSG));
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardButton button1 = new KeyboardButton();
        button1.setText(ShowBotMessage(BotMessage.MENU_BTN_MSG));
        KeyboardButton button2 = new KeyboardButton();
        button2.setText(ShowBotMessage(BotMessage.BYD_BTN_MSG));
        row.add(button1);
        row.add(button2);
        KeyboardButton button3 = new KeyboardButton();
        button3.setText(ShowBotMessage(BotMessage.ORDERS_BTN_MSG));
        KeyboardButton button4 = new KeyboardButton();
        button4.setText(ShowBotMessage(BotMessage.BRANCH_BTN_MSG));
        row1.add(button3);
        row1.add(button4);
        KeyboardButton button5 = new KeyboardButton();
        button5.setText(ShowBotMessage(BotMessage.CONTACT_BTN_MSG));
        KeyboardButton button6 = new KeyboardButton();
        button6.setText(ShowBotMessage(BotMessage.SETTINGS_BTN_MSG));
        row2.add(button5);
        row2.add(button6);
        rows.add(row);
        rows.add(row1);
        rows.add(row2);
        replyKeyboardMarkup.setKeyboard(rows);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        Message execute = execute(sendMessage);
        telegramUser.setState(BotState.MAIN_SECTION);
        saveTelegramUserToDb();
    }

    private void saveTelegramUserToDb() {
        telegramUserRepo.save(telegramUser);
    }

    private ReplyKeyboard generateContactBtn() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardButton button = new KeyboardButton();
        button.setText(ShowBotMessage(BotMessage.SHARE_YOUR_CONTACT_BTN_MSG));
        button.setRequestContact(true);
        row.add(button);
        rows.add(row);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(rows);
        return replyKeyboardMarkup;
    }

    private String ShowBotMessage(BotMessage botMessage) {
        if (telegramUser.getSelectedLang().equals(BotCallBackData.SET_LANG_UZB.toString())) {
            return botMessage.getTextUzb();
        } else if (telegramUser.getSelectedLang().equals(BotCallBackData.SET_LANG_ENG.toString())) {
            return botMessage.getTextEng();
        } else {
            return null;
        }
    }

    private TelegramUser findUserByChatId(Long chatId) {
        TelegramUser telegramUser = telegramUserRepo.findByChatId(chatId);
        return Objects.requireNonNullElseGet(telegramUser, () -> telegramUserRepo.save(TelegramUser.builder()
                .chatId(chatId)
                .state(BotState.START)
                .build()));
    }

    @Override
    public String getBotUsername() {
        return "t.me/chill_burger_bot";
    }

    @Override
    public String getBotToken() {
        return "6727833668:AAG0IzbrQUjv19buC80ic7n4nLyrEwr25bs";
    }
}
