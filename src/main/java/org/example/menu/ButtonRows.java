package org.example.menu;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ButtonRows {
    public static InlineKeyboardButton createButton(String text, Button idButton){
        InlineKeyboardButton button = new InlineKeyboardButton(text);
        button.setCallbackData(idButton.value());
        return button;


    }
    public static List<InlineKeyboardButton> createRowInLine(InlineKeyboardButton ...buttons){
        return new ArrayList<>(Arrays.asList(buttons));
    }
    public static  List<List<InlineKeyboardButton>> createRowsInLine(List<InlineKeyboardButton> ...rows){
        return new ArrayList<>(Arrays.asList(rows));
    }
}
