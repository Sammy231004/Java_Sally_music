package org.example.menu;

public enum Button {
    ABOUT("ABOUT"), NEXT("NEXT"), BACK("BACK"), START("START");
    private String idButton;
    Button(String idButton){
        this.idButton = idButton;
    }

    public String value(){
        return this.idButton;
    }

}
