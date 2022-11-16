// Вариант 8
// Необходимо реализовать класс согласно
// варианту. Необходимо придумать поля для вашего варианта: числовые (целое и
// вещественное, не менее двух) и текстовые (не менее двух). Все поля класса
// должны быть закрытыми, необходимо реализовать методы доступа. В классе
// должны быть реализованы конструктор по-умолчанию и с параметрами.

import java.util.*;

/**
 * Класс Main используется для управления объектами мебели.
 */
public class Main {

    /**
     * Основное меню программы.
     */
    private static void printMenu() {

        System.out.print("""
                ╭────────────────────────────────────╮
                │                МЕНЮ                │
                ╰────────────────────────────────────╯
                1 — Добавить пустой предмет мебели
                2 — Добавить предмет мебели с параметрами
                3 — Изменить предмет мебели
                4 — Удалить предмет мебели
                5 — Узнать параметры доставки предмета мебели
                6 — Сортировать мебель
                7 — Вывести всю мебель
                8 — Выход \n \n""");
    }

    /**
     * Меню изменения полей объекта.
     */
    private static void printEditMenu() {

        System.out.print("""
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

        System.out.print("""
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
     * Функция проверяет, является ли введенное число двойной точности
     * и зацикливается до получения корректного числа.
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

    /**
     * Вывод всех объектов из списка.
     * @param furnitureStack Список объектов.
     */
    private static void printAllFurniture(ArrayList<Furniture> furnitureStack) {

        int count = 1;
        if (furnitureStack.isEmpty()) {
            System.out.println("Мебели нет!");
        }
        else {
            for(Furniture i: furnitureStack) {
                System.out.println("#" + count + i.toString());
                count++;
            }
        }
    }

    /**
     * Добавление объекта с данными, заполненными пользователем.
     * @return Объект с данными, заполненными пользователем.
     */
    private static Furniture getNewFurnDetails() {

        Scanner input = new Scanner(System.in);

        System.out.println("Введите название мебели:");
        String furnitureName = input.nextLine();

        System.out.println("Введите цвет мебели:");
        String furnitureColor = input.nextLine();

        System.out.println("Введите длину мебели в сантиметрах:");
        int furnitureLength = input.nextInt();

        while (furnitureLength < 1) {
            System.out.println("Длина должна быть больше 0! Введите новую длину: ");
            furnitureLength = getIntInput();
        }

        System.out.println("Введите цену мебели:");
        double furniturePrice = getDoubleInput();
        while (furniturePrice <= 0) {
            System.out.println("Цена должна быть больше 0! Введите новую цену:");
            furniturePrice = getDoubleInput();
        }
        return new Furniture(furnitureName, furnitureColor,furnitureLength, furniturePrice);
    }

    /**
     * Изменение данных объекта.
     * @param furnitureToEdit Объект для изменения.
     * @param editOperation Поле для изменения.
     */
    private static void editFurnVar(Furniture furnitureToEdit, int editOperation) throws RangeException, EmptyStringException, NegNumException {

        Scanner input = new Scanner(System.in);

        switch (editOperation) {
            case 1 -> {
                System.out.println("Введите новое имя:");
                String newName = input.nextLine();
                try {
                    furnitureToEdit.setName(newName);
                } catch (EmptyStringException e) {
                    System.out.println("Ошибка при задании имени");
                }
            }

            case 2 -> {
                System.out.println("Введите новый цвет:");
                String newColor = input.nextLine();
                try {
                    furnitureToEdit.setColor(newColor);
                } catch (EmptyStringException e) {
                    // Установка значения по-умолчанию.
                    furnitureToEdit.setColor("NoColor");
                    System.out.println(e.getMessage());
                    break;
                }
                System.out.println("Новый цвет задан!");
            }
            case 3 -> {
                System.out.println("Введите новую длину:");
                int newLength = input.nextInt();

                try {
                    furnitureToEdit.setLength(newLength);
                } catch (NegNumException e) {
                    // Установка значения по-умолчанию.
                    furnitureToEdit.setLength(0);
                    System.out.println(e.getMessage());
                    break;
                }
                System.out.println("Новая длина задана!");
            }
            case 4 -> {
                System.out.println("Введите новую цену:");
                double newPrice = input.nextDouble();

                try {
                    furnitureToEdit.setPrice(newPrice);
                } catch (Exception e) {
                    // Установка значения по-умолчанию.
                    furnitureToEdit.setPrice(0.0);
                    System.out.println(e.getMessage());
                    break;
                }
                System.out.println("Новая цена задана!");
            }
            case 5 -> System.out.println("отмена");
            default -> System.out.println("Данного пункта нет в меню!");
        }
    }

    /**
     * Сортировка объектов по полям.
     * @param furnitureStack Список с объектами.
     */
    private static void sortAllFurniture(ArrayList<Furniture> furnitureStack) {
        printAllFurniture(furnitureStack);
        if (!furnitureStack.isEmpty()) {
            printSortMenu();
            System.out.println("Выберите по какому полю сортировать мебель:");
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
                default -> System.out.println("Данного пункта нет в меню!");
            }
            System.out.println("Мебель отсортирована!");
            printAllFurniture(furnitureStack);
        }
    }

    /**
     * Функция изменяет поля объекта.
     * @param furnitureStack Список с объектами.
     */
    private static void editFurniture(ArrayList<Furniture> furnitureStack) throws RangeException, EmptyStringException, NegNumException {

        printAllFurniture(furnitureStack);

        if (!furnitureStack.isEmpty()) {
            System.out.println("Выберите какую мебель изменить:");
            int numToEdit = getIntInput();

            if (numToEdit < 1 || numToEdit > furnitureStack.size()) {
                System.out.println("Введите корректный номер мебели для изменения! (от 1 до " +
                        furnitureStack.size() + ")");
            }
            else {
                printEditMenu();
                int editOperation = getIntInput();
                editFurnVar(furnitureStack.get(numToEdit - 1), editOperation);
            }
        }
    }

    /**
     * Функция удаляет объект по выбору пользователя.
     * @param furnitureStack Список с объектами.
     */
    private static void deleteFurniture(ArrayList<Furniture> furnitureStack) {

        printAllFurniture(furnitureStack);

        if (!furnitureStack.isEmpty()) {
            System.out.println("Введите число мебели для удаления");
            int numToDelete = getIntInput();

            if (numToDelete < 1 || numToDelete > furnitureStack.size()) {
                System.out.println("Выберите корректный номер мебели для удаления! (от 1 до " +
                        furnitureStack.size() + ")");
            }
            else {
                furnitureStack.remove(numToDelete - 1);
                System.out.println("Мебель #"+ numToDelete + " удалена!");
            }
        }
    }

    /**
     * Функция получает информацию о доставках объектов.
     * @param furnitureStack Список с объектами.
     */
    private static void getDeliveryDet(ArrayList<Furniture> furnitureStack) {

        if (!furnitureStack.isEmpty()) {
            int count = 1;
            for(Furniture i: furnitureStack) {
                if (i.getLength() != null) {
                    System.out.println("#" + count + " | Имя - " + i.getName()
                            + " | Тип доставки - " + i.getShippingInfo());
                    count++;
                }
                else {
                    System.out.println("#" + count + " | " + i.getName() + " | Размеры мебели не заданы!");
                }
            }
        }
        else {
            System.out.println("Мебели нет!");
        }
    }

    /**
     * Главная функция программы с возможностью вывода информации и
     * реализацией алгоритма.
     * @param args массив последовательностей символов (строк),
     *      *            которые передаются в функцию main.
     * @throws EmptyStringException Исключение пустой строки.
     * @throws NegNumException Исключение отрицательного числа.
     * @throws RangeException Исключение диапазона числа.
     */
    public static void main(String[] args) throws EmptyStringException, NegNumException, RangeException {

        ArrayList<Furniture> furnitureStack = new ArrayList<>();
        int menuChoice;

        do {
            printMenu();
            System.out.println("Введите Ваш выбор:");
            menuChoice = getIntInput();

            switch (menuChoice) {
                case 1 -> {
                    Furniture newFurniture = new Furniture();

                    furnitureStack.add(newFurniture);
                    System.out.println("Пустой предмет мебели добавлен!");
                }
                case 2 -> {
                    furnitureStack.add(getNewFurnDetails());
                    System.out.println("Предмет мебели с параметрами добавлен!");
                }
                case 3 -> editFurniture(furnitureStack);
                case 4 -> deleteFurniture(furnitureStack);
                case 5 -> getDeliveryDet(furnitureStack);
                case 6 -> sortAllFurniture(furnitureStack);
                case 7 -> printAllFurniture(furnitureStack);
                case 8 -> System.out.println("До свидания!");
                default -> System.out.println("Данного пункта нет в меню!");
            }
        } while (menuChoice != 8);
    }
}