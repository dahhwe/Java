import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static void printMenu() {

        System.out.print("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃                МЕНЮ                ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                1 — Добавить Пустую Мебель
                2 — Добавить Заполненную Мебель
                3 — Узнать Параметры Доставки Мебели
                4 — Вывести Всю Мебель
                5 — Выход \n \n""");
    }

    /**
     * Функция проверяет, является ли введенное число целочисленным
     * и зацикливается до получения корректного числа.
     * @return userInt, целое число
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
                System.out.println("Некорректный ввод! Введите число:");
                userInput = input.nextLine();
            }
        } while (!allowedInput);
        return userInt;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ArrayList<Furniture> furnitureStack = new ArrayList<Furniture>();
        int menuChoice;

        do {
            printMenu();
            System.out.println("Введите Ваш выбор:");
            menuChoice = getIntInput();

            switch (menuChoice) {
                case 1 -> {
                    Furniture newFurniture = new Furniture();

                    furnitureStack.add(newFurniture);
                    System.out.println(furnitureStack);
                }
                case 2 -> {

                    System.out.println("Введите (int)длину мебели:");
                    int furnitureLength = input.nextInt();

                    System.out.println("Введите (double)цену мебели:");
                    double furniturePrice = getDoubleInput();

                    System.out.println("Введите имя мебели:");
                    String furnitureName = input.next();

                    System.out.println("Введите цвет мебели:");
                    String furnitureColor = input.next();

                    Furniture newFurniture = new Furniture(furnitureLength, furniturePrice,
                            furnitureName, furnitureColor);

                    furnitureStack.add(newFurniture);
                    System.out.println(furnitureStack);
                }
                case 3 -> {
                    System.out.println("Текст или фраза не заполнены!\n");
                    }
                case 4 -> {
                    int count = 1;
                    for(Furniture f: furnitureStack) {
                        System.out.println("№" + count + " | " + f.getLength() + " | " + f.getPrice() + " | " +
                                f.getName() + " | " + f.getColor());
                        count++;
                    }
                }

                case 5 -> System.out.println("До свидания!");
                default -> System.out.println("Данного пункта нет в меню!");
            }
        } while (menuChoice != 5);

    }
}