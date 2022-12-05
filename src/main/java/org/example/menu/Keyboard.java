package org.example.menu;

import API.models.Album;
import org.example.helper.BuildMessageMedia;
import org.example.helper.StreamFile;
import org.example.menu.buildKeyboard.BuildKeyboard;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageMedia;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class Keyboard {

    private static final String MENU  = "/photo/menu.jpg";
    private static final String CHART_TRACK  = "/photo/chartTrack.jpg";
    private static final String CHART_AUTHOR  = "/photo/chartArtist.jpg";
    private static final String SEARCH  = "/photo/search.jpg";

    public StreamFile drawMenu(String chatId) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(BuildKeyboard.buildMenu());
        InputStream file = this.getClass().getResourceAsStream(MENU);

        return BuildMessageMedia.buildMessageMedia(chatId, markup, file, "menu.jpg",
                "Привет уважаемый пользователь");
    }

    public StreamFile drawMenu(String chatId, int messageId){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(BuildKeyboard.buildMenu());
        InputStream file = this.getClass().getResourceAsStream(MENU);

        return BuildMessageMedia.buildEditMessageMedia(chatId, markup, messageId, file,
                "menu.jpg", "Привет уважаемый пользователь");
    }

    public StreamFile drawAbout(String chatId, int messageId){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(BuildKeyboard.buildAbout());
        InputStream file = this.getClass().getResourceAsStream(MENU);
        String captionAbout = """
                version 1.0
                name SallyMusic
                author Виталий Савиных
                """;


        return BuildMessageMedia.buildEditMessageMedia(chatId, markup, messageId, file, "menu.jpg", captionAbout);
    }

    public StreamFile drawChartMusic(String chatId, int messageId, String chartMusic){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(BuildKeyboard.buildChartMusic());
        InputStream readFile = this.getClass().getResourceAsStream(CHART_TRACK);
        String captionChartMusic = "Топ-10 треков\n\n"+chartMusic;

        return BuildMessageMedia.buildEditMessageMedia(chatId,
                markup,
                messageId,
                readFile,
                "chartTrack.jpg",
                captionChartMusic);
    }

    public StreamFile drawChartArtist(String chatId, int messageId, String chartArtist){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(BuildKeyboard.buildChartArtist());
        InputStream inputStream = this.getClass().getResourceAsStream(CHART_AUTHOR);
        String captionChartArtist = "Топ-10 музыкантов\n\n"+chartArtist;

        return BuildMessageMedia.buildEditMessageMedia(chatId, markup, messageId, inputStream,
                "chartArtist.jpg", captionChartArtist);
    }

    public StreamFile drawSearch(String chatId, int messageId){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(BuildKeyboard.buildSearchMenu());
        InputStream inputStream = this.getClass().getResourceAsStream(SEARCH);

        return BuildMessageMedia.buildEditMessageMedia(chatId, markup, messageId, inputStream, "search.jpg", "");
    }

    public StreamFile drawSearchTrack(String chatId, int messageId){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(BuildKeyboard.buildSearchTrackOrArtist());
        InputStream inputStream = this.getClass().getResourceAsStream(SEARCH);
        String captionSearchTrack = "Для поиска трека,\nвведите имя трека и его автора\nпо форме:\n/t name - author";

        return BuildMessageMedia.buildEditMessageMedia(chatId, markup, messageId,
                inputStream, "search.jpg", captionSearchTrack);
    }

    public StreamFile drawSearchArtist(String chatId, int messageId){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(BuildKeyboard.buildSearchTrackOrArtist());
        InputStream inputStream = this.getClass().getResourceAsStream(SEARCH);
        String captionSearchArtist = "Для поиска музыканта, введите имя музыканта по форме:\n/a artist";

        return BuildMessageMedia.buildEditMessageMedia(chatId, markup, messageId,
                inputStream, "search.jpg", captionSearchArtist);
    }

    //НЕ РАБОТАЕТ! Из-за ограничение телеги, блок не показывается
    public StreamFile drawFindTrack(String chatId, String message){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(BuildKeyboard.buildSearchTrackOrArtist());
        InputStream inputStream = this.getClass().getResourceAsStream(SEARCH);

        return BuildMessageMedia.buildMessageMedia(chatId, markup, inputStream, "search.jpg", message);
    }

    public StreamFile drawFindArtist(String chatId,String message, List<Album> albums){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(BuildKeyboard.buildSearchAuthor(albums));
        InputStream inputStream = this.getClass().getResourceAsStream(SEARCH);

        return BuildMessageMedia.buildMessageMedia(chatId, markup,
                inputStream, "search.jpg", message);
    }

    public StreamFile drawFindArtist(String chatId, String message, List<Album> albums, int messageId){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(BuildKeyboard.buildSearchAuthor(albums));
        InputStream inputStream = this.getClass().getResourceAsStream(SEARCH);

        return BuildMessageMedia.buildEditMessageMedia(chatId, markup, messageId, inputStream, "search.jpg", message);
    }

    public StreamFile drawTrackInAlbum(String chatId, int messageId, String text)
    {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(BuildKeyboard.buildTracksInAlbum());
        InputStream inputStream = this.getClass().getResourceAsStream(SEARCH);

        return BuildMessageMedia.buildEditMessageMedia(chatId, markup, messageId,
                inputStream, "search.jpg", text);
    }


}
