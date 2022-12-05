package org.example.menu;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ButtonRows {

    private static InlineKeyboardButton createButton(Button button){
        InlineKeyboardButton buttonKeyboard = new InlineKeyboardButton(button.getText());
        buttonKeyboard.setCallbackData(button.getIdButton().value());
        return buttonKeyboard;
    }

    private static InlineKeyboardButton createButtonAlbums(Button button){
        InlineKeyboardButton buttonKeyboard = new InlineKeyboardButton(button.getText());
        buttonKeyboard.setCallbackData(button.getButtonId());
        return buttonKeyboard;
    }

    public static List<List<InlineKeyboardButton>> createKeyboard(List<Button> ...buttons){
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        for (var item1:buttons){
            List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
            for (var item2:item1){
                inlineKeyboardButtons.add(createButton(item2));
            }
            rows.add(inlineKeyboardButtons);
        }

        return rows;
    }

    public static List<List<InlineKeyboardButton>> keyboardForAlbums(List<List<Button>> buttons){
        List<List<InlineKeyboardButton>> rowsForAlbums = new ArrayList<>();
        for (var item: buttons){
            List<InlineKeyboardButton> rowTemp = new ArrayList<>();
            for (var button: item){
                rowTemp.add(createButtonAlbums(button));
            }
            rowsForAlbums.add(rowTemp);
        }

        List<InlineKeyboardButton> backButtonRow = new ArrayList<>();
        backButtonRow.add(createButton(new Button("<< Назад", ButtonId.BACK_SEARCH)));
        rowsForAlbums.add(backButtonRow);

        return rowsForAlbums;
    }
}
