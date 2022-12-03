package org.example.menu;

public class Button {
    private String text;
    private ButtonId idButton;

    public Button(String text, ButtonId idButton) {
        this.text = text;
        this.idButton = idButton;
    }

    public String getText() {
        return text;
    }

    public ButtonId getIdButton() {
        return idButton;
    }
}
