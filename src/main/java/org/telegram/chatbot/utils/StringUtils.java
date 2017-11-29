package org.telegram.chatbot.utils;

public class StringUtils {
    public static boolean isCommand(String string) {
        return string.startsWith("/");
    }

    public static boolean isNumber(String number) {
        return number.matches("[0-9]+");
    }

    public static boolean isValidNumberForGame(String number) {
        return isNumber(number) && number.matches("^(?!.*(.).*\\1)\\d{3}$") && number.charAt(0)!='0';
    }

    public static int digitsMatches(String first, String second) {
        int count = 0;
        for (String s : first.split("")) {
            for (String s1 : second.split("")) {
                if (s.equals(s1))
                    count++;
            }
        }
        return count;
    }

    public static int placesMatches(String first, String second) {
        String[] f = first.split("");
        String[] s = second.split("");

        int count = 0;
        for (int i = 0; i < f.length; i++) {
            if (f[i].equals(s[i]))
                count++;
        }
        return count;
    }
}
