import java.util.*;

/**
 * Реализация Кольцевого двунаправленного списка.
 */
public class Main {

    /**
     * Бирюзовый цвет текста
     */
    public static final String ANSI_CYAN = "\u001B[36m";
    /**
     * Стандартный цвет текста
     */
    public static final String ANSI_RESET = "\u001B[0m";

    private static CircularDoublyLinkedList linkedList;

    /**
     * Основное меню программы.
     */
    private static void printMenu() {

        System.out.print("""
                ╭────────────────────────────────────╮
                │                МЕНЮ                │
                ╰────────────────────────────────────╯
                1 — Создать список
                2 — Добавить элемент в список
                3 — Обнулить список
                4 — Проверить, список пуст / не пуст
                5 — Установить указатель в начало списка
                6 — Установить указатель в конец списка
                7 — Добавить элемент за указателем
                8 — Добавить элемент до указателя
                9 — Удалить элемент за указателем
                10 — Удалить элемент до указателя
                11 — Переместить указатель вправо
                12 — Переместить указатель влево
                13 — Обменять значения элементов до указателя и за указателем
                14 — Вывод списка на экран
                15 — Выход \n \n""");
    }

    private static void printListTypeMenu() {
        System.out.print("""
                ╭────────────────────────────────────╮
                │          НАСТРОЙКИ СПИСКА          │
                ╰────────────────────────────────────╯
                1 — Создать список содержащий элементы типа Integer
                2 — Создать список содержащий элементы типа Double
                3 — Создать список содержащий элементы типа Character
                4 — Создать список содержащий элементы типа String
                5 — Назад \n \n""");
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
     * Главная функция программы с возможностью вывода информации и
     * реализацией алгоритма.
     *
     * @param args массив последовательностей символов (строк),
     *             которые передаются в функцию main.
     */
    public static void main(String[] args) {

        printListTypeMenu();
        int menuChoice, listType = 0;

        do {
            printMenu();
            System.out.println("Введите Ваш выбор:");
            menuChoice = getIntInput();

            switch (menuChoice) {
                case 1 -> {
                    System.out.println("Введите элемент для добавления в список:");
                    listType = getLinkedListConfig();
                    createNewList(listType);
                }
                case 2 -> {
                    if (linkedList != null) addElem(listType);
                }
                case 3 -> {
                    if (linkedList != null)
                        linkedList.clear();
                    System.out.println("Список обнулен");
                }
                case 4 -> {
                    if (linkedList == null) System.out.println("Список пуст");
                    else System.out.println("Список содержит элементы");
                }
                case 5 -> {
                    if (linkedList != null) linkedList.setPointerAtFront();
                }
                case 6 -> {
                    if (linkedList != null) linkedList.setPointerAtBack();
                }
                case 7 -> addDataAtPointer(listType, true);
                case 8 -> addDataAtPointer(listType, false);
                case 9 -> delElem(true);
                case 10 -> delElem(false);
                case 11 -> shiftPointer(true);
                case 12 -> shiftPointer(false);
                case 13 -> swapElems();
                case 14 -> printDoublyList();
                case 15 -> System.out.println("До свидания!");
                default -> System.out.println("Данного пункта нет в меню!");
            }
        } while (menuChoice != 15);
    }

    private static void addElem(int listType) {

        if (linkedList != null) {
            Scanner input = new Scanner(System.in);
            Object valToAdd = getValToAdd(listType);

            if (valToAdd != null) {
                linkedList.add(valToAdd);
                System.out.println("Элемент добавлен");
            }
        } else
            System.out.println("Список не задан!");
    }


    private static int getLinkedListConfig() {
        printListTypeMenu();
        int userChoice = getIntInput();
        if (1 <= userChoice && userChoice <= 5) {
            return userChoice;
        }
        return -1;
    }

    private static void delElem(boolean flag) {
        if (linkedList != null) {
            if (flag) linkedList.delElemAfterPointer();
            else linkedList.delElemBeforePointer();
        } else System.out.println("Список не задан!");

    }

    private static void swapElems() {
        if (linkedList != null) {
            linkedList.swapNearElems();
        } else System.out.println("Список не задан!");

    }

    private static void shiftPointer(boolean flag) {
        if (linkedList != null) {
            if (flag) linkedList.shiftPointerR();
            else linkedList.shiftPointerL();
        } else System.out.println("Список не задан!");

    }


    private static void printDoublyList() {
        if (linkedList != null) {
            System.out.println("\n─────────────────────────────────────");
            for (int i = 0; i < linkedList.size(); ++i) {
                if (i == linkedList.getPointerIndex()) {
                    System.out.println(ANSI_CYAN + linkedList.get(i) + ANSI_RESET);
                } else
                    System.out.println(linkedList.get(i));
            }
        } else {
            System.out.println("Список не задан!");
        }
    }

    /**
     * Функция проверяет, является ли введенное число двойной точности
     * и зацикливается до получения корректного числа.
     *
     * @return userDouble, число двойной точности.
     */
    private static double getDoubleInput() {

        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        double userDouble = 0.0;
        boolean allowedInput = false;

        do {
            try {
                userDouble = Double.parseDouble(userInput);
                allowedInput = true;
            } catch (NumberFormatException ex) {
                System.out.println("Некорректный ввод! Введите double:");
                userInput = input.nextLine();
            }
        } while (!allowedInput);
        return userDouble;
    }

    private static void createNewList(int listType) {

        switch (listType) {
            case 1, -1 -> linkedList = new CircularDoublyLinkedList<Integer>();
            case 2 -> linkedList = new CircularDoublyLinkedList<Double>();
            case 3 -> linkedList = new CircularDoublyLinkedList<Character>();
            case 4 -> linkedList = new CircularDoublyLinkedList<String>();
            default -> {
            }
        }
    }

    private static void addDataAtPointer(int listType, Boolean flag) {

        Object valToAdd = getValToAdd(listType);

        if (valToAdd != null) {
            if (flag) linkedList.addAfterPointer(valToAdd);
            else linkedList.addBeforePointer(valToAdd);
        }
    }

    private static Object getValToAdd(int listType) {

        Scanner input = new Scanner(System.in);
        Object valToAdd = null;

        switch (listType) {
            case 1, -1 -> {
                System.out.println("Введите целое число:");
                valToAdd = getIntInput();
            }
            case 2 -> {
                System.out.println("Введите число с плавающей запятой:");
                valToAdd = getDoubleInput();
            }
            case 3 -> {
                System.out.println("Введите символ:");
                valToAdd = input.next().charAt(0);
            }
            case 4 -> {
                System.out.println("Введите строку:");
                valToAdd = input.nextLine();
            }
            default -> {
            }
        }
        return valToAdd;
    }
}
