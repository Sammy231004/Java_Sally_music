package org.example.menu.buildKeyboard;

import API.models.Album;
import org.example.menu.Button;
import org.example.menu.ButtonId;
import org.example.menu.ButtonRows;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class BuildKeyboard {

    public static List<List<InlineKeyboardButton>> buildMenu() {
        var rowOne = List.of(new Button("Поиск", ButtonId.SEARCH));
        var rowTwo = List.of(new Button("Топ-10 музыкантов", ButtonId.TOP_ARTIST));
        var rowThree = List.of(new Button("Топ-10 треков", ButtonId.TOP_MUSIC));
        var rowFourth = List.of(new Button("О боте", ButtonId.ABOUT));

        return ButtonRows.createKeyboard(rowOne, rowTwo, rowThree, rowFourth);
    }

    public static List<List<InlineKeyboardButton>> buildAbout(){
        return ButtonRows.createKeyboard(backButton());
    }

    public static List<List<InlineKeyboardButton>> buildChartMusic() {
        return ButtonRows.createKeyboard(backButton());
    }

    public static List<List<InlineKeyboardButton>> buildChartArtist() {
        return ButtonRows.createKeyboard(backButton());
    }

    public static List<List<InlineKeyboardButton>> buildSearchMenu(){
        var rowOne = List.of(new Button("Поиск музыканта", ButtonId.SEARCH_ARTIST));
        var rowTwo = List.of(new Button("Поиск музыки", ButtonId.SEARCH_MUSIC));
        var rowThree = backButton();
        return ButtonRows.createKeyboard(rowOne, rowTwo, rowThree);
    }

    public static List<List<InlineKeyboardButton>> buildSearchTrackOrArtist(){
        return ButtonRows.createKeyboard(List.of(new Button("<< Назад", ButtonId.BACK_SEARCH)));
    }

    public static List<List<InlineKeyboardButton>> buildSearchAuthor(List<Album> albumList){
        List<List<Button>> buttons = new ArrayList<>();
        for (var item: albumList)
            buttons.add(List.of(new Button(item.getName(), item.getId())));

        return ButtonRows.keyboardForAlbums(buttons);

    }

    public static List<List<InlineKeyboardButton>> buildTracksInAlbum(){
        return ButtonRows.createKeyboard(List.of(new Button("<< Назад", ButtonId.BACK_ARTIST)));
    }

    private static List<Button> backButton(){
        return List.of(new Button("<< Назад", ButtonId.BACK));
    }

}
