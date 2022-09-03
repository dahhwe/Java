// Вариант 8
// Входные данные: текст и строка.
// Результат работы алгоритма:
// массив слов входного текста, которые в качестве подстроки содержат входную строку.
// Например, если пользователь введет "рек" для строки "Ехал Грека через реку",
// результат будет "Грека, реку".

import java.util.Arrays;
import java.util.Scanner;


public class Main {
    private static void printMenu() {

        System.out.print("""
                —————————————————————————————————————
                                МЕНЮ
                —————————————————————————————————————
                1. Ввести Текст
                2. Ввести фразу для поиска
                3. Поиск слов включающих в себя фразу
                4. Выход \n \n""");
    }

    private static int getIntInput() {
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        boolean valAccepted = false;
        int acceptedInt;

        while (!valAccepted) {
            try {
                acceptedInt = Integer.parseInt(userInput);
                valAccepted = true;
                return acceptedInt;
            }
            catch (NumberFormatException ex) {
                valAccepted = false;
                System.out.println("Некорректный ввод! Введите число:");
                userInput = input.nextLine();
            }
        }
        return 0;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int menuChoice;
        String[] sentenceWords = new String[0];
        String phrase = "", sentence = "";

        do {
            printMenu();
            System.out.println("Введите Ваш выбор:");
            menuChoice = getIntInput();

            switch (menuChoice) {
                case 1 -> {
                    System.out.println("Введите Ваш текст:");
                    sentence = input.nextLine();
                    sentenceWords = sentence.split(" ");
                    System.out.println(Arrays.toString(sentenceWords));
                }
                case 2 -> {
                    System.out.println("Введите фразу для поиска:");
                    phrase = input.nextLine();
                }
                case 3 -> {
                    int numOfFoundWords = 0;
                    if (!(sentence.isEmpty() & phrase.isEmpty())) {
                        for (String sentenceWord : sentenceWords) {
                            if (sentenceWord.contains(phrase)) {
                                numOfFoundWords += 1;
                            }
                        }

                        String[] foundWordsArray = new String[numOfFoundWords];

                        for (String sentenceWord : sentenceWords) {
                            if (sentenceWord.contains(phrase)) {
                                for (int j = 0; j < foundWordsArray.length; j++) {
                                    if (foundWordsArray[j] == null) {
                                        foundWordsArray[j] = sentenceWord;
                                        break;
                                    }
                                }
                            }
                        }

                        if (foundWordsArray.length > 0) {
                            System.out.println("Найдены слова!: " +
                                    Arrays.toString(foundWordsArray).replace("[", "").replace("]", " ") + "\n");
                        } else {
                            System.out.println("Слова содержащие фразу '" + phrase + "' не были найдены\n");
                        }

                    } else {
                        System.out.println("Текст или фраза не заполнены!\n");
                    }
                }
                case 4 -> System.out.println("До свидания!");
                default -> System.out.println("Данного пункта нет в меню!");
            }
        } while (menuChoice != 4);
    }
}
