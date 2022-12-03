package org.example.menu.buildKeyboard;

import org.example.menu.Button;
import org.example.menu.ButtonId;
import org.example.menu.ButtonRows;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class BuildKeyboard {

    public static List<List<InlineKeyboardButton>> buildMenu(){
        var rowOne = List.of(new Button("О боте", ButtonId.ABOUT));
        var rowTwo = List.of(new Button("Начать", ButtonId.START));

        return ButtonRows.createKeyboard(rowOne, rowTwo);
    }
}
