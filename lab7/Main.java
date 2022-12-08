// Вариант 8
// Необходимо реализовать класс согласно
// варианту. Необходимо придумать поля для вашего варианта: числовые (целое и
// вещественное, не менее двух) и текстовые (не менее двух). Все поля класса
// должны быть закрытыми, необходимо реализовать методы доступа. В классе
// должны быть реализованы конструктор по-умолчанию и с параметрами.

import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;

/**
 * Класс Main используется для управления объектами мебели.
 */
public class Main {

    /**
     * Список всех профессий
     */
    private static ArrayList<Furniture> furnitureStack = new ArrayList<>();

    /**
     * Поток вывода данных (поддерживает русский язык)
     */
    private final static PrintStream out = new PrintStream(System.out, true);

    /**
     * Считывание данных из консоли
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Основное меню программы.
     */
    private static void printMenu() {

        out.print("""
                ╭────────────────────────────────────╮
                │                МЕНЮ                │
                ╰────────────────────────────────────╯
                1  — Добавить пустой предмет мебели
                2  — Добавить предмет мебели с параметрами
                3  — Изменить предмет мебели
                4  — Удалить предмет мебели
                5  — Удалить дубликаты *
                6  — Сгруппировать мебель по цвету *
                7  — Узнать параметры доставки предмета мебели
                8  — Сортировать мебель
                9  — Фильтр мебели по цене *
                10 — Рассчитать общую стоимость *
                11 — Найти самую длинную мебель
                12 — Вывести всю мебель *
                13 — Выход \n \n""");
    }

    /**
     * Меню изменения полей объекта.
     */
    private static void printEditMenu() {

        out.print("""
                ╭────────────────────────────────────╮
                │        Изменение параметров        │
                ╰────────────────────────────────────╯
                1 — Изменить имя
                2 — Изменить цвет
                3 — Изменить длину
                4 — Изменить цену
                5 — Отмена \n \n""");
    }

    /**
     * Меню сортировки объектов по полям.
     */
    private static void printSortMenu() {

        out.print("""
                ╭────────────────────────────────────╮
                │         Сортировка мебели          │
                ╰────────────────────────────────────╯
                1 — Сортировать по имени
                2 — Сортировать по цвету
                3 — Сортировать по длине
                4 — Сортировать по цене
                5 — Отмена \n \n""");
    }

    /**
     * Функция для получения целочисленного ввода.
     *
     * @return Целое число.
     */
    private static int getIntInput() {

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
     * Функция для получения строки от ввода пользователя.
     *
     * @return Введенная строка.
     */
    private static String getStringInput() {

        String userInput = scanner.nextLine();

        while (userInput.isEmpty()) {
            out.println("Ввод не может быть пустым! Повторите ввод:");
            userInput = scanner.nextLine();
        }
        return userInput;
    }

    /**
     * Функция проверяет, является ли введенное число двойной точности
     * и зацикливается до получения корректного числа.
     *
     * @return userDouble, число двойной точности.
     */
    private static double getDoubleInput() {

        String userInput = scanner.nextLine();
        double userDouble = 0.0;
        boolean allowedInput = false;

        do {
            try {
                userDouble = Double.parseDouble(userInput);
                allowedInput = true;
            } catch (NumberFormatException ex) {
                out.println("Некорректный ввод! Введите double:");
                userInput = scanner.nextLine();
            }
        } while (!allowedInput);
        return userDouble;
    }

    /**
     * Добавление объекта с данными, заполненными пользователем.
     *
     * @return Объект с данными, заполненными пользователем.
     */
    private static Furniture getNewFurnDetails() {
        out.println("Введите имя мебели:");
        String furnitureName = getStringInput();

        out.println("Введите цвет мебели:");
        String furnitureColor = getStringInput();

        out.println("Введите длину мебели в сантиметрах:");
        int furnitureLength = getIntInput();

        while (furnitureLength < 1) {
            out.println("Длина должна быть больше 0! Введите новую длину: ");
            furnitureLength = getIntInput();
        }

        out.println("Введите цену мебели:");
        double furniturePrice = getDoubleInput();
        while (furniturePrice <= 0) {
            out.println("Цена должна быть больше 0! Введите новую цену:");
            furniturePrice = getDoubleInput();
        }
        return new Furniture(furnitureName, furnitureColor, furnitureLength, furniturePrice);
    }

    /**
     * Изменение данных объекта.
     *
     * @param furnitureToEdit Объект для изменения.
     * @param editOperation   Поле для изменения.
     */
    private static void editFurnVar(Furniture furnitureToEdit, int editOperation) {
        switch (editOperation) {
            case 1 -> {
                out.println("Введите новое имя:");
                String newName = getStringInput();

                furnitureToEdit.setName(newName);
                out.println("Новое имя задано!");
            }
            case 2 -> {
                out.println("Введите новый цвет:");
                String newColor = getStringInput();

                furnitureToEdit.setColor(newColor);
                out.println("Новый цвет задан!");
            }
            case 3 -> {
                out.println("Введите новую длину:");
                int newLength = getNewLength();

                furnitureToEdit.setLength(newLength);
                out.println("Новая длина задана!");
            }
            case 4 -> {
                out.println("Введите новую цену:");
                double newPrice = getNewPrice();

                furnitureToEdit.setPrice(newPrice);
                out.println("Новая цена задана!");
            }
            case 5 -> out.println("отмена");
            default -> out.println("Данного пункта нет в меню!");
        }
    }

    /**
     * Функция проверяет введенную новую цену на корректный ввод.
     *
     * @return Новая цена.
     */
    private static double getNewPrice() {
        double newPrice = getDoubleInput();
        while (newPrice <= 0) {
            out.println("Цена должна быть больше 0! Введите новую цену:");
            newPrice = getDoubleInput();
        }
        return newPrice;
    }

    /**
     * Функция проверяет введенную новую длину на корректный ввод.
     *
     * @return Новая длина.
     */
    private static int getNewLength() {
        int newLength = getIntInput();
        while (newLength < 1) {
            out.println("Длина должна быть больше 0! Введите новую длину:");
            newLength = getIntInput();
        }
        return newLength;
    }

    /**
     * Функция изменяет поля объекта.
     */
    private static void editFurniture() {
        printAllFurniture(furnitureStack);

        if (!furnitureStack.isEmpty()) {
            out.println("Выберите какую мебель изменить:");
            int numToEdit = getIntInput();

            if (numToEdit < 1 || numToEdit > furnitureStack.size()) {
                out.println("Введите корректный номер мебели для изменения! (от 1 до " +
                        furnitureStack.size() + ")");
            } else {
                printEditMenu();
                int editOperation = getIntInput();
                editFurnVar(furnitureStack.get(numToEdit - 1), editOperation);
            }
        }
    }

    /**
     * Функция удаляет объект по выбору пользователя.
     */
    private static void deleteFurniture() {
        printAllFurniture(furnitureStack);

        if (!furnitureStack.isEmpty()) {
            out.println("Введите число мебели для удаления");
            int numToDelete = getIntInput();

            if (numToDelete < 1 || numToDelete > furnitureStack.size()) {
                out.println("Выберите корректный номер мебели для удаления! (от 1 до " +
                        furnitureStack.size() + ")");
            } else {
                furnitureStack.remove(numToDelete - 1);
                out.println("Мебель #" + numToDelete + " удалена!");
            }
        }
    }

    /**
     * Изъятие из списка дубликатов.
     */
    private static void deleteDuplicates() {
        Stream<Furniture> stream = furnitureStack.stream().distinct();
        int defLen = furnitureStack.size();

        furnitureStack = stream.collect(Collectors.toCollection(ArrayList<Furniture>::new));
        int remLen = defLen - furnitureStack.size();
        if (remLen > 0) {
            out.printf("%d дубликатов было удалено \n", remLen);
        } else {
            out.println("дубликатов нет \n");
        }
    }

    /**
     * Группировка мебели по цвету.
     */
    private static void groupByColor() {
        Map<String, List<Furniture>> furnByColor = furnitureStack.stream().
                collect(Collectors.groupingBy(Furniture::getColor));

        Map<String, Long> furnByColorGroup = furnitureStack.stream().
                collect(Collectors.groupingBy(Furniture::getColor, counting()));

        for (String key : furnByColor.keySet()) {
            out.printf("Цвет: %s, количество %d: \n", key, furnByColorGroup.get(key));
            for (Furniture item : furnByColor.get(key)) {
                out.println(item);
            }
        }
    }

    /**
     * Функция получает информацию о доставках объектов.
     */
    private static void getDeliveryDet() {
        if (!furnitureStack.isEmpty()) {
            int count = 1;
            for (Furniture i : furnitureStack) {
                if (i.getLength() != null) {
                    out.println("#" + count + " | Имя - " + i.getName()
                            + " | Тип доставки - " + i.getShippingInfo());
                    count++;
                } else {
                    out.println("#" + count + " | " + i.getName() + " | Размеры мебели не заданы!");
                }
            }
        } else {
            out.println("Мебели нет!");
        }
    }

    /**
     * Сортировка объектов по полям.
     */
    private static void sortAllFurniture() {
        printAllFurniture(furnitureStack);

        if (!furnitureStack.isEmpty()) {
            printSortMenu();
            out.println("Выберите по какому полю сортировать мебель:");
            int numToEdit = getIntInput();

            switch (numToEdit) {
                case 1 -> {
                    Comparator<Furniture> nameComparator = Comparator.comparing(Furniture::getName);
                    furnitureStack.sort(nameComparator);
                }
                case 2 -> {
                    Comparator<Furniture> colorComparator = Comparator.comparing(Furniture::getColor);
                    furnitureStack.sort(colorComparator);
                }
                case 3 -> {
                    Comparator<Furniture> lengthComparator = Comparator.comparing(Furniture::getLength);
                    furnitureStack.sort(lengthComparator);
                }
                case 4 -> {
                    Comparator<Furniture> priceComparator = Comparator.comparing(Furniture::getPrice);
                    furnitureStack.sort(priceComparator);
                }
                default -> out.println("Данного пункта нет в меню!");
            }
            out.println("Мебель отсортирована!");
            printAllFurniture(furnitureStack);
        }
    }

    /**
     * Вывод цены выше заданного минимума.
     */
    private static void filterByPrice() {
        out.print("Введите минимальную цену мебели для поиска: ");
        double minFurnPrice = getDoubleInput();

        Stream<Furniture> furnItems = furnitureStack.stream().filter(furniture -> furniture.getPrice() >= minFurnPrice);
        List<Furniture> filteredFurniture = furnItems.collect(Collectors.toCollection(ArrayList::new));
        printAllFurniture(filteredFurniture);
    }

    /**
     * Демонстрация умения работать с операциями сведения с накоплением.
     * Вывод общей стоимости всей мебели в списке.
     */
    private static void getTotalCost() {
        long start = 0;
        long totalCost = furnitureStack.stream().reduce(start, (value, furniture) ->
                (long) (value + furniture.getPrice()), Long::sum);

        out.println("Общая стоимость всей мебели: " + totalCost);
    }

    /**
     * Вывод самой длинной мебели в списке.
     */
    private static void findLongestFurn() {
        Optional<Furniture> largest = furnitureStack.stream().max(Furniture::compareTo);
        largest.ifPresent(furniture -> System.out.println("Самая длинная мебель: " + furniture));
    }

    /**
     * Вывод всех объектов из списка.
     */
    private static void printAllFurniture(List<Furniture> furnitureStack) {
        Stream<Furniture> furnitureStream = furnitureStack.stream();
        furnitureStream.forEach(out::println);
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
            out.println("Введите Ваш выбор:");
            menuChoice = getIntInput();

            switch (menuChoice) {
                case 1 -> {
                    Furniture newFurniture = new Furniture();

                    furnitureStack.add(newFurniture);
                    out.println("Пустой предмет мебели добавлен!");
                }
                case 2 -> {
                    furnitureStack.add(getNewFurnDetails());
                    out.println("Предмет мебели с параметрами добавлен!");
                }
                case 3 -> editFurniture();
                case 4 -> deleteFurniture();
                case 5 -> deleteDuplicates();
                case 6 -> groupByColor();
                case 7 -> getDeliveryDet();
                case 8 -> sortAllFurniture();
                case 9 -> filterByPrice();
                case 10 -> getTotalCost();
                case 11 -> findLongestFurn();
                case 12 -> printAllFurniture(furnitureStack);
                case 13 -> out.println("До свидания!");
                default -> out.println("Данного пункта нет в меню!");
            }
        } while (menuChoice != 13);
    }
}