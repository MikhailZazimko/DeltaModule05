package com.javarush.spring10.console;

import java.util.Locale;

public class Runner {
    public static void main(String[] args) {
        ResManager resManager = ResManager.INSTANCE;
        Locale ruLocale = new Locale("ru", "RU");
        resManager.setLocale(ruLocale);
        System.out.println(resManager.get(Message.GREETING));
        System.out.println(resManager.get(Message.QUESTION));
        System.out.println(resManager.get(User.FIRST_NAME));
        System.out.println(resManager.get(User.LAST_NAME));
    }
}
