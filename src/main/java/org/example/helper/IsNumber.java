package org.example.helper;

public class IsNumber {
    public static boolean isNumber(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }
}
