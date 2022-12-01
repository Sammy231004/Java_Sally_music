package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "SallyMusic";
    }

    @Override
    public String getBotToken() {
        return "5927852684:AAGFYcW1Y_gXRgCGjhVk-ZqQtglKlCVk2rU";
    }

    @Override
    public void onUpdateReceived(Update update) {
      Long userid =update.getMessage().getChatId();
      sendMessage("Выберите функцию, которая вам нужна",String.valueOf(userid));

    }
    private void sendMessage(String message, String chatId){
        SendMessage sendMessage = new SendMessage(chatId, message);
        try{
            execute(sendMessage);
        }catch (TelegramApiException telegramApiException){
            System.out.println(telegramApiException.getMessage());
        }
    }
}

