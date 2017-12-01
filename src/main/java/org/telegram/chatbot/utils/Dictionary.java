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
        dictionary.add("мені стидно");
    }

    public static String RandomPhrase(){
        Random random = new Random();
        return dictionary.get(random.nextInt(dictionary.size()-1));
    }
}
