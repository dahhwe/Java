import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static void printMenu() {

        System.out.print("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃                МЕНЮ                ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                1 — Добавить мебель
                2 — Добавить мебель с параметрами
                3 — Изменить мебель
                4 — Удалить мебель
                5 — Узнать параметры доставки мебели
                6 — Вывести всю мебель
                7 — Выход \n \n""");
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

    private static void printAllFurniture(ArrayList<Furniture> furnitureStack) {
        int count = 1;
        if (furnitureStack.isEmpty()) {
            System.out.println("Мебели нет!");
        }
        else {
            for(Furniture i: furnitureStack) {
                System.out.println("№" + count + ". | " + i.getLength() + " | " + i.getPrice() + " | " +
                        i.getName() + " | " + i.getColor());
                count++;
            }
        }
    }

    private static Furniture getNewFurnDetails() {

        Scanner input = new Scanner(System.in);
        Furniture newFurniture = new Furniture();
        //
        System.out.println("Введите (int)длину мебели:");
        int furnitureLength = input.nextInt();

        System.out.println("Введите (double)цену мебели:");
        double furniturePrice = getDoubleInput();

        System.out.println("Введите имя мебели:");
        String furnitureName = input.next();

        System.out.println("Введите цвет мебели:");
        String furnitureColor = input.next();

        return new Furniture(furnitureLength, furniturePrice, furnitureName, furnitureColor);
    }

    private static void printEditMenu() {
        System.out.print("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃        Изменение параметров        ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                1 — Изменить длину
                2 — Изменить цену
                3 — Изменить название
                4 — Изменить цвет
                5 — Отмена \n \n""");
    }

    private static void editFurnVar(Furniture furnitureToEdit, int editOperation) {
        Scanner input = new Scanner(System.in);

        switch (editOperation) {
            case 1 -> {
                System.out.println("Введите новую длину:");
                int newLength = input.nextInt();
                furnitureToEdit.setLength(newLength);
                System.out.println("Новая длина задана!");
            }
            case 2 -> {
                System.out.println("Введите новую цену:");
                double newPrice = input.nextDouble();
                furnitureToEdit.setPrice(newPrice);
                System.out.println("Новая цена задана!");
            }
            case 3 -> {
                System.out.println("Введите новое имя:");
                String newName = input.nextLine();
                furnitureToEdit.setName(newName);
                System.out.println("Новое имя задано!");
            }
            case 4 -> {
                System.out.println("Введите новый цвет:");
                String newColor = input.nextLine();
                furnitureToEdit.setColor(newColor);
                System.out.println("Новый цвет задан!");
            }
            case 5 -> {
                furnitureToEdit.setName("Ты лох");
            }
            default -> System.out.println("");

        }

        //furnitureStack.set(numToEdit - 1, new Furniture() ) .color = "red";

    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
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

                    System.out.println("Мебель " + newFurniture + " добавлена!");
                }
                case 2 -> {
                    furnitureStack.add(getNewFurnDetails());
                    System.out.println(furnitureStack);
                }
                case 3 -> {
                    printAllFurniture(furnitureStack);

                    System.out.println("Введите число мебели для изменения");
                    int numToEdit = input.nextInt();

                    printEditMenu();
                    System.out.println("");
                    int editOperation = input.nextInt();
                    editFurnVar(furnitureStack.get(numToEdit - 1), editOperation);
                }
                case 4 -> {
                    printAllFurniture(furnitureStack);

                    System.out.println("Введите число мебели для удаления");
                    int numToDelete = input.nextInt();
                    furnitureStack.remove(numToDelete - 1);

                    System.out.println("Мебель удалена!");

                }
                case 5 -> System.out.println("Delivery club premium!");
                case 6 -> printAllFurniture(furnitureStack);
                case 7 -> System.out.println("До свидания!");
                default -> System.out.println("Данного пункта нет в меню!");
            }
        } while (menuChoice != 7);

    }
}