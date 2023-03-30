package com.javarush.spring12.console;

import com.javarush.spring12.console.Message;
import com.javarush.spring12.console.ResManager;
import com.javarush.spring12.console.User;

import java.util.Locale;

public class Runner {
    public static void main(String[] args) {
        ResManager resManager = ResManager.INSTANCE;
        Locale ruLocale = new Locale("ru", "RU");
        resManager.setLocale(ruLocale);
        System.out.println(resManager.get(Message.GREETING));
        System.out.println(resManager.get(Message.QUESTION));
        System.out.println(resManager.get(com.javarush.spring12.console.User.FIRST_NAME));
        System.out.println(resManager.get(User.LAST_NAME));
    }
}