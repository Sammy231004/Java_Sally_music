package org.example;

import API.MusixMatch;
import API.exceptions.ArtistNotFound;
import API.exceptions.TrackNotFound;
import API.models.Artist;
import org.example.helper.IsNumber;
import org.example.helper.StreamFile;
import org.example.menu.ButtonId;
import org.example.menu.Keyboard;
import org.example.processingData.ProcessingResponse;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;


public class TelegramBot extends TelegramLongPollingBot {

    private final MusixMatch API = new MusixMatch("5f0ab8375d4a3ab3ac1617167cb538b7");

    private final ProcessingResponse response = new ProcessingResponse(API);


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
        Keyboard keyboard = new Keyboard();
        String command = update.getMessage().getText();
        String chatId = update.getMessage().getChatId().toString();

        if (command.equals("/start")){
            sendPhotoMessage(keyboard.drawMenu(chatId));
        }

        if (command.startsWith("/a")){
            String author = command.replace("/a", "").trim();
            sendResponseForFindArtist(author, chatId);
        } else if (command.startsWith("/t")) {

            String str = command.replace("/t", "").trim();
            String track = str.substring(0, str.indexOf("-")).trim();
            String author = str.substring(str.indexOf("-")+1).trim();

            sendResponseForFindTrack(author, track, chatId);
        }
    }
    private void processingButtonCommand(CallbackQuery callbackQuery, Keyboard keyboard){
        String chatId = callbackQuery.getMessage().getChatId().toString();
        int messageId = callbackQuery.getMessage().getMessageId();

        if (IsNumber.isNumber(callbackQuery.getData())){
            String editMessage = callbackQuery.getMessage().getCaption();
            String artist = editMessage.substring(editMessage.indexOf("-")+1).trim();
            sendTracksInAlbum(callbackQuery.getData(), artist, chatId, messageId);
            return;
        }
        ButtonId buttonId = ButtonId.valueOf(callbackQuery.getData());

        switch (buttonId){
            case TOP_ARTIST:
                try{
                    sendEditPhotoMessage(keyboard.drawChartArtist(chatId, messageId, response.chartArtist()));
                }catch (Exception ex){
                    sendText("Произошла неизвестная ошибка", chatId);
                }
                break;

            case TOP_MUSIC:
                try{
                    sendEditPhotoMessage(keyboard.drawChartMusic(chatId, messageId, response.chartMusic()));
                }catch (Exception ex){
                    sendText("Произошла неизвестная ошибка", chatId);
                }
                break;

            case SEARCH:

            case BACK_SEARCH:
                sendEditPhotoMessage(keyboard.drawSearch(chatId, messageId));
                break;

            case BACK_ARTIST:
                String caption = callbackQuery.getMessage().getCaption();
                String artist = caption.substring(caption.indexOf("-")+1,  caption.indexOf("+")).trim();
                sendResponseForFindArtist(artist, chatId, messageId);
                break;

            case SEARCH_ARTIST:
                sendEditPhotoMessage(keyboard.drawSearchArtist(chatId, messageId));
                break;

            case SEARCH_MUSIC:
                sendEditPhotoMessage(keyboard.drawSearchTrack(chatId, messageId));
                break;

            case ABOUT:
                sendEditPhotoMessage(keyboard.drawAbout(chatId, messageId));
                break;

            case BACK:
                sendEditPhotoMessage(keyboard.drawMenu(chatId, messageId));
                break;
        }
    }

    private void sendTracksInAlbum(String album, String artist, String chatId, int messageId){
        Keyboard keyboard = new Keyboard();
        int albumId = Integer.parseInt(album);
        try{
            String message = response.getTracksFromAlbum(albumId, artist);
            sendEditPhotoMessage(keyboard.drawTrackInAlbum(chatId, messageId, message));
        }catch (Exception ex){
            sendText("Произошла ошибка", chatId);
        }
    }

    private void sendResponseForFindArtist(String artist, String chatId){
        Keyboard keyboard = new Keyboard();

        try{
            sendText("Идет поиск, подождите...", chatId);
            Artist findArtist = response.findArtist(artist);
            String message = "Найден артист - "+findArtist.getName();
            sendPhotoMessage(keyboard.drawFindArtist(chatId, message, findArtist.getAlbumList()));
        }catch (ArtistNotFound ex){
            sendText(ex.getMessage(), chatId);
        }catch (IOException | InterruptedException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void sendResponseForFindArtist(String artist, String chatId, int messageId){
        Keyboard keyboard = new Keyboard();
        try{
            Artist findArtist = response.findArtist(artist);
            String message = "Найден артист - "+findArtist.getName();
            sendEditPhotoMessage(keyboard.drawFindArtist(chatId, message, findArtist.getAlbumList(), messageId));
        }catch (Exception ex){
            sendText("Произошла ошибка", chatId);
        }
    }

    private void sendResponseForFindTrack(String author, String track, String chatId){
        Keyboard keyboard = new Keyboard();
        try{
            sendText("Идет поиск, подождите...", chatId);
            sendText(response.findTrack(author, track), chatId);
            sendPhotoMessage(keyboard.drawFindTrack(chatId, "В ожидании..."));
        }catch (TrackNotFound ex){
            sendText(ex.getMessage(), chatId);
        }catch (IOException | InterruptedException ex) {
            System.out.println(ex.getMessage());
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

    private void sendPhotoMessage(StreamFile message){
        try{
            execute(message.getSendPhoto());
            message.closeStream();
        }catch (TelegramApiException telegramApiException){
            System.out.println(telegramApiException.getMessage());
        }
    }

    private void sendEditPhotoMessage(StreamFile message){
        try {
            execute(message.getEditMessageMedia());
            message.closeStream();
        }catch (TelegramApiException telegramApiException){
            System.out.println(telegramApiException.getMessage());
        }
    }
}

