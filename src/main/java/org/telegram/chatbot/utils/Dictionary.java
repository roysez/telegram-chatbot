package org.telegram.chatbot.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dictionary {

    private static List<String> dictionary = new ArrayList<>();

    static {
        dictionary.add("Кончена бурса, але мені подобається");
        dictionary.add("як справи?))");
        dictionary.add("А мені в джаві все не подобаєтья, обриганська");
        dictionary.add("впринципі да");
        dictionary.add("сьогодні має бути фіфа\nтому готуйтесь");
        dictionary.add("сука дайте хтось ножа");
        dictionary.add("давайте банк сперми відкриєм\nАле внатурі");
        dictionary.add("доброго дня хлопці)");
        dictionary.add("завтра йдете на лекції?");
        dictionary.add("Вот и день прошeл");
        dictionary.add("цілуй підлогу я тут був");
        dictionary.add("застрэлить мене з гарматы");
        dictionary.add("Все номрально\n" +
                "нехуй було на пз поступати шоб вчитись");
        dictionary.add("Сосиски в миски");
        dictionary.add("Завтра нічого не треба мутити до старого?");
        //  dictionary.add("найгірше - це бачити в мітапі на який хочеш сходити парочку 2 єбанутих дівчин");
        dictionary.add("досить цікава хуйня. навіть я б сказав дуже");
        dictionary.add("Ви поступили в елітний вуз, звикайте до хуйні");
        dictionary.add("Та й таке");
        dictionary.add("гляньте як надворі зірки світяться");
        dictionary.add("Вільна каса!");
        dictionary.add("треба виєбати цих стипендіатів");
        dictionary.add("Ще 50 хвилин можна ригати");
        dictionary.add("Завтра на першу пару))");
        dictionary.add("мені стидно");
        dictionary.add("Дмитро, винеси стіл");

        dictionary.add(" ");
    }

    public static String randomPhrase(){
        Random random = new Random();
        return dictionary.get(random.nextInt(dictionary.size()-1));
    }
}
