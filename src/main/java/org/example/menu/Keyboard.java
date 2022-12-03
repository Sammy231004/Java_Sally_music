package org.example.menu;

import org.example.helper.StreamFile;
import org.example.menu.buildKeyboard.BuildKeyboard;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class Keyboard {
    public StreamFile drawMenu(String chatId) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        SendPhoto menuMessage = new SendPhoto();
        InputStream file = this.getClass().getResourceAsStream("/photo/menu.jpg");

        markup.setKeyboard(BuildKeyboard.buildMenu());
        menuMessage.setChatId(Long.valueOf(chatId));
        menuMessage.setCaption("Привет уважаемый пользователь");
        menuMessage.setReplyMarkup(markup);
        menuMessage.setPhoto(new InputFile().setMedia(file, "menu.jpg"));

        return new StreamFile(menuMessage, file);
    }


}
