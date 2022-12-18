package org.example;

import API.MusixMatch;
import API.exceptions.ArtistNotFound;
import API.exceptions.TrackNotFound;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;

public class Main {
    public static void main(String[] args){
        try {
            start();
        }catch (TelegramApiException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void start() throws TelegramApiException {
        TelegramBot telegramBot = new TelegramBot();
        TelegramBotsApi telegramBotsApi = null;
        telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(telegramBot);
    }
}