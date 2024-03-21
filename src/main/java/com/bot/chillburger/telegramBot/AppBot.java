package com.bot.chillburger.telegramBot;

import com.bot.chillburger.enums.BotCallBackData;
import com.bot.chillburger.entity.TelegramUser;
import com.bot.chillburger.enums.BotMessage;
import com.bot.chillburger.enums.BotState;
import com.bot.chillburger.repository.TelegramUserRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AppBot extends TelegramLongPollingBot {

    TelegramUser telegramUser = null;
    String adminChatId = "1539471133";
    private final TelegramUserRepo telegramUserRepo;


    @SneakyThrows
    @Autowired
    public AppBot(TelegramBotsApi api, TelegramUserRepo telegramUserRepo) {
        this.telegramUserRepo = telegramUserRepo;
        api.registerBot(this);
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            Long chatId = message.getChatId();
            telegramUser = findUserByChatId(chatId);
            if (message.hasText()) {
                String text = message.getText();
                if (text.equals("/start")) {
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
                } else if (telegramUser.getState().equals(BotState.SELECT_CITY)) {
                    DeleteMessage deleteMessage = new DeleteMessage();
                    deleteMessage.setChatId(chatId);
                    deleteMessage.setMessageId(telegramUser.getCityMsgId());
                    execute(deleteMessage);
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
                    } else if (text.equals(ShowBotMessage(BotMessage.SETTINGS_BTN_MSG))) {

                    }
                }else if(telegramUser.getState().equals(BotState.CONTACT_ADMIN)){
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(adminChatId);
                    sendMessage.setText(text);
                    execute(sendMessage);
                    sendSelectSectionMsg(chatId);
                }
            } else if (message.hasContact() && telegramUser.getState().equals(BotState.SHARE_CONTACT)) {
                Contact contact = message.getContact();
                String x = contact.getPhoneNumber();
                String phoneNumber = x.startsWith("+998") ? x : "+998" + x;
                telegramUser.setPhone(phoneNumber);
                deleteContactMsgId(chatId);
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
                telegramUser.setCityMsgId(execute.getMessageId());
                saveTelegramUserToDb();
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
                telegramUser.setContactMessageId(execute.getMessageId());
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
            }
        }
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
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        Message execute = execute(sendMessage);
        telegramUser.setState(BotState.MAIN_SECTION);
        telegramUser.setMainSecBtnsId(execute.getMessageId());
        saveTelegramUserToDb();
    }

    private void deleteContactMsgId(Long chatId) throws TelegramApiException {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(chatId);
        deleteMessage.setMessageId(telegramUser.getContactMessageId());
        execute(deleteMessage);
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
