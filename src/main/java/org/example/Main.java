package org.example;

import API.MusixMatch;
import API.exceptions.ArtistNotFound;
import API.exceptions.TrackNotFound;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, ArtistNotFound {
        String apiKey = "5f0ab8375d4a3ab3ac1617167cb538b7";
        /*TelegramBot telegramBot = new TelegramBot();
        TelegramBotsApi telegramBotsApi = null;

        try {
            telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(telegramBot);
        } catch (TelegramApiException ex) {
            System.out.println(ex.getMessage());
        }*/

        MusixMatch musixMatch = new MusixMatch(apiKey);
        for (var item:musixMatch.searchAlbumsByArtist("Snoop Dogg")){
            System.out.println(item);
        }

        System.out.println("\n\n");

        for (var item:musixMatch.getTracksFromAlbum(33982881)){
            System.out.println(item);
        }
    }
}