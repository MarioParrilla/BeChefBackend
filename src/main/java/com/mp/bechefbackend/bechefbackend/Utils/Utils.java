package com.mp.bechefbackend.bechefbackend.Utils;

public class Utils {

    public static String takeRandomChar(){
        String[] c = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","1","2","3","4","5","6","7","8","9","0"};
        return c[ (int) (Math.random()  *  c.length - 1) ];
    }

}
