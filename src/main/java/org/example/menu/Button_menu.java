package org.example.menu;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class Button_menu {
    public SendMessage drawMenu(String chatId) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        var buttonAbout = ButtonRows.createButton("О боте",Button.ABOUT);
        var buttonStart = ButtonRows.createButton("Начать", Button.START);
        var buttonBack = ButtonRows.createButton("Назад", Button.BACK);
        var buttonNext = ButtonRows.createButton("Далее", Button.NEXT);
        var row = ButtonRows.createRowInLine(buttonAbout,buttonStart,buttonBack,buttonNext);
        var rows = ButtonRows.createRowsInLine(row);
        markup.setKeyboard(rows);
        SendMessage menuMessage = new SendMessage();
        menuMessage.setChatId(Long.valueOf(chatId));
        menuMessage.setText("Привет уважаемый пользователь");
        menuMessage.setReplyMarkup(markup);
        return menuMessage;




    }

//    public EditMessageText drawMenu(){
//
//  }

}
