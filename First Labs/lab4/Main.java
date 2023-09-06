//Необходимо описать интерфейс, содержащий одну функцию
//
// int analyse(String str);
// Данный интерфейс производит анализ строки по интересующему критерию.
// Необходимо реализовать два класса для этого интерфейса.
// Первый класс должен возвращать количество гласных букв в строке,
// а второй − возвращать количество согласных букв в строке.
// Необходимо, чтобы приложение запросило у пользователя строку и выдало результаты вычисления для обоих классов.
// В строке должны быть только символы латинского алфавита, символов кириллицы быть не должно.

import java.util.Scanner;

/**
 * Класс Main используется для управления объектами двигателей.
 */
public class Main {

    /**
     * Меню программы.
     */
    private static void printMenu() {

        System.out.print("""
                ╭────────────────────────────────────╮
                │           Меню программы           │
                ├────────────────────────────────────┤
                │ 1 — Выполнение алгоритма           │
                │ 2 — Выход                          │
                ╰────────────────────────────────────╯ \n \n""");
    }

    /**
     * Функция для получения целочисленного ввода.
     *
     * @return Целое число.
     */
    private static int getIntInput() {

        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        int userInt = 0;
        boolean allowedInput = false;

        do {
            try {
                userInt = Integer.parseInt(userInput);
                allowedInput = true;
            } catch (NumberFormatException ex) {
                System.out.println("Некорректный ввод! Введите целое число:");
                userInput = input.nextLine();
            }
        } while (!allowedInput);
        return userInt;
    }

    /**
     * Функция для получения строки от ввода пользователя.
     *
     * @return Введенная строка.
     */
    private static String getEngStringInput() {

        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

            while (userInput.isEmpty() || !checkValidString(userInput)) {
                System.out.println("Некорректный ввод! Повторите ввод:");
                userInput = input.nextLine();
            }
        return userInput;
    }

    /**
     *  Функция проверяет строку на присутствие ЛАТИН
     * @param userInput Строка пользователя.
     * @return Результат проверки строки.
     */
    private static boolean checkValidString(String userInput) {
        return userInput.matches("[a-zA-Z0-9 ]+");
    }


    /**
     * Главная функция программы с возможностью вывода информации и
     * реализацией алгоритма.
     *
     * @param args массив последовательностей символов (строк),
     *             которые передаются в функцию main.
     */
    public static void main(String[] args) {

        int menuChoice;
        do {
            printMenu();
            System.out.println("Введите Ваш выбор:");
            menuChoice = getIntInput();

            switch (menuChoice) {
                case 1 -> calculateLetters();
                case 2 -> System.out.println("До свидания!");
            }
        } while (menuChoice != 2);
    }

    /***
     * Функция вычисляет количество гласных и согласных букв в строке.
     */
    private static void calculateLetters() {

        System.out.println("Введите строку:");
        String userString = getEngStringInput();
        String lowerUserString = userString.toLowerCase();

        Consonants consonants = new Consonants();
        Vowels vowels = new Vowels();

        int numOfConsonants = consonants.analyse(lowerUserString);
        int numOfVowels = vowels.analyse(lowerUserString);

        System.out.printf("""
                Количество гласных букв в строке — %d\s
                Количество согласных букв в строке — %d\s
                """, numOfVowels, numOfConsonants);
    }
}