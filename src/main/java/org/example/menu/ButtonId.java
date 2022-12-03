package org.example.menu;

public enum ButtonId {
    ABOUT("ABOUT"), NEXT("NEXT"), BACK("BACK"), START("START");
    private String idButton;
    ButtonId(String idButton){
        this.idButton = idButton;
    }

    public String value(){
        return this.idButton;
    }

}
