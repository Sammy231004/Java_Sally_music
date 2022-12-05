package org.example.menu;

public enum ButtonId {
    ABOUT("ABOUT"),
    BACK("BACK"),
    BACK_SEARCH("BACK_SEARCH"),
    BACK_ARTIST("BACK_ARTIST"),
    SEARCH("SEARCH"),
    SEARCH_ARTIST("SEARCH_ARTIST"),
    SEARCH_MUSIC("SEARCH_MUSIC"),
    TOP_ARTIST("TOP_ARTIST"),
    TOP_MUSIC("TOP_MUSIC"),
    START("START");
    private String idButton;
    ButtonId(String idButton){
        this.idButton = idButton;
    }

    public String value(){
        return this.idButton;
    }

}
