import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * \
 * Студенты (потоки) сдают экзамен. В аудитории N парт и один стол экзаменатора (разделяемые ресурсы).
 * Студенты подходят к экзаменатору в произвольном порядке и садятся сдавать экзамен.
 * Далее экзаменатор либо ставит студенту оценку, либо выгоняет его, либо отправляет еще раз подумать
 * за парту (это состояние является случайной величиной с равномерным законом). Когда аудитория освобождается,
 * заходит новая группа студентов. Значение N задается пользователем при старте приложения.
 */
public class Main {
    /**
     * Поток вывода данных.
     */
    private final static PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    /**
     * Основное меню программы.
     */
    private static void printMenu() {
        out.print("""
                ╭────────────────────────────────────╮
                │                МЕНЮ                │
                ╰────────────────────────────────────╯
                1 — Начать экзамен
                2 — Выход \n \n""");
    }

    /**
     * Функция для получения целочисленного ввода.
     *
     * @return Целое число.
     */
    private static int getIntInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        int userInt = 0;
        boolean allowedInput = false;

        do {
            try {
                userInt = Integer.parseInt(userInput);
                allowedInput = true;
            } catch (NumberFormatException ex) {
                out.println("Некорректный ввод! Введите целое число:");
                userInput = scanner.nextLine();
            }
        } while (!allowedInput);
        return userInt;
    }

    /**
     * Начало экзамена.
     */
    private static void startExam() {
        out.print("Сколько парт в аудитории? ");

        int numOfDesks = getIntInput();
        while (numOfDesks < 1) {
            out.println("Число парт не может быть меньше 1! Сколько парт в аудитории?");
            numOfDesks = getIntInput();
        }
        try {
            new Exam().examStart(numOfDesks);
        } catch (InterruptedException ignored) {
        }
    }

    /**
     * Главная функция программы с возможностью вывода информации и
     * реализацией алгоритма.
     *
     * @param args Массив последовательностей символов (строк), которые передаются в функцию main.
     */
    public static void main(String[] args) {

        int menuChoice;
        do {
            printMenu();
            out.println("Введите Ваш выбор:");
            menuChoice = getIntInput();

            switch (menuChoice) {
                case 1 -> startExam();
                case 2 -> out.println("До свидания!");
                default -> out.println("Данного пункта нет в меню!");
            }
        } while (menuChoice != 2);
    }
}