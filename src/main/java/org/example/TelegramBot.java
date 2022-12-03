package org.example;

import API.MusixMatch;
import org.example.menu.ButtonId;
import org.example.menu.Keyboard;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;



public class TelegramBot extends TelegramLongPollingBot {

    private final MusixMatch API = new MusixMatch("5f0ab8375d4a3ab3ac1617167cb538b7");

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
        if(update.hasMessage()){
            processingTextCommand( update);

        } else if (update.hasCallbackQuery());
        {
            processingButtonCommand(update.getCallbackQuery(), new Keyboard());
        }
    }
    private void processingTextCommand(Update update){
        Keyboard buttonMenu_1 = new Keyboard();
        String command = update.getMessage().getText();
        String chatId = update.getMessage().getChatId().toString();

        if (command.equals("/start")){
            sendMessage(buttonMenu_1.drawMenu(chatId),chatId);
        }
    }
    private void processingButtonCommand(CallbackQuery callbackQuery, Keyboard buttonMenu){
        String chatId = callbackQuery.getMessage().getChatId().toString();
        ButtonId buttonId = ButtonId.valueOf(callbackQuery.getData());
        switch (buttonId){
            case START:
                break;
            case ABOUT:
                break;
            case BACK:
                break;
            case NEXT:
                break;

        }





    }
    private void sendText(String message, String chatId){
        SendMessage sendMessage = new SendMessage(chatId, message);
        try{
            execute(sendMessage);
        }catch (TelegramApiException telegramApiException){
            System.out.println(telegramApiException.getMessage());
        }
    }
    private void sendMessage(SendMessage message, String chatId){

        try{
            execute(message);
        }catch (TelegramApiException telegramApiException){
            System.out.println(telegramApiException.getMessage());
        }
    }
}

