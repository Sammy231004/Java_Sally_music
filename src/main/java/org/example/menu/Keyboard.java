package org.example.menu;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class Keyboard {
    public SendMessage drawMenu(String chatId) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        var rowOne = List.of(new Button("О боте", ButtonId.ABOUT));
        var rowTwo = List.of(new Button("Начать", ButtonId.START));
        var rowThree = List.of(
                new Button("<<", ButtonId.BACK),
                new Button(">>", ButtonId.NEXT));


        markup.setKeyboard(ButtonRows.createKeyboard(rowOne, rowTwo, rowThree));
        SendMessage menuMessage = new SendMessage();
        menuMessage.setChatId(Long.valueOf(chatId));
        menuMessage.setText("Привет уважаемый пользователь");
        menuMessage.setReplyMarkup(markup);
        return menuMessage;

        /*InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        var buttonAbout = ButtonRows.createButton("О боте", ButtonId.ABOUT);
        var buttonStart = ButtonRows.createButton("Начать", ButtonId.START);
        var buttonBack = ButtonRows.createButton("Назад", ButtonId.BACK);
        var buttonNext = ButtonRows.createButton("Далее", ButtonId.NEXT);
        var row = ButtonRows.createRowInLine(buttonAbout,buttonStart,buttonBack,buttonNext);
        var rows = ButtonRows.createRowsInLine(row);
        markup.setKeyboard(rows);
        SendMessage menuMessage = new SendMessage();
        menuMessage.setChatId(Long.valueOf(chatId));
        menuMessage.setText("Привет уважаемый пользователь");
        menuMessage.setReplyMarkup(markup);
        return menuMessage;*/


    }

//    public EditMessageText drawMenu(){
//
//  }

}
