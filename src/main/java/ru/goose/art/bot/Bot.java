package ru.goose.art.bot;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    final private String BOT_TOKEN = "6886181306:AAHEKxCZkPxvY6OiTdZTBDeLEtsKodW3Arw";
    final private String BOT_NAME = "GooseArtBot";
    String chat_id = "-4065502656";
    public Bot()
    {

    }
    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
    @Override
    public void onUpdateReceived(Update update) {
        // Проверяем появление нового сообщения в чате, и если это текст

    }

    public void sendAds(String text) throws TelegramApiException {
        SendMessage ad = new SendMessage();
        ad.setChatId(chat_id);
        ad.setText("Новая заявка: " + text);
        execute(ad);
    }
}
