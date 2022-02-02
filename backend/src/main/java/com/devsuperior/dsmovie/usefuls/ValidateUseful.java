package com.devsuperior.dsmovie.usefuls;

public class ValidateUseful {

    public static boolean isNullOrEmpty(Object obj) {
        return obj == null || obj.toString().isEmpty();
    }
}
