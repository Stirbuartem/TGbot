package org.example;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Random;

public class TelegramBotHandler extends TelegramLongPollingBot {
    private String botUsername = "first33457567665bot";
    private String botToken = "6718011164:AAGSu3WnsgB9xMX5TyA6SV7Zazz5cBn0_ik";

    private GuessNumberGame guessNumberGame = new GuessNumberGame();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() == true) {
            Message messageFromUser = update.getMessage();

            if (messageFromUser.hasText() == true) {
                String textFromUser = messageFromUser.getText();
                long chatId = messageFromUser.getChatId();

                String textToUser = guessNumberGame.processGameLogic(textFromUser);

                SendMessage messageToUser = new SendMessage();
                messageToUser.setChatId(chatId);
                messageToUser.setText(textToUser);

                try {
                    execute(messageToUser);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}