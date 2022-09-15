import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static void printMenu() {

        System.out.print("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃                МЕНЮ                ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                1 — Добавить пустой предмет мебели
                2 — Добавить предмет мебели с параметрами
                3 — Изменить предмет мебели
                4 — Удалить предмет мебели
                5 — Узнать параметры доставки предмета мебели
                6 — Сортировать мебель
                7 — Вывести всю мебель
                8 — Выход \n \n""");
    }

    private static void printEditMenu() {
        System.out.print("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃        Изменение параметров        ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                1 — Изменить имя
                2 — Изменить цвет
                3 — Изменить длину
                4 — Изменить цену
                5 — Отмена \n \n""");
    }

    private static void printSortMenu() {
        System.out.print("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃         Сортировка мебели          ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                1 — Сортировать по имени
                2 — Сортировать по цвету
                3 — Сортировать по длине
                4 — Сортировать по цене
                5 — Отмена \n \n""");
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
                System.out.println("Некорректный ввод! Введите целое число:");
                userInput = input.nextLine();
            }
        } while (!allowedInput);
        return userInt;
    }

    private static String getStringInput() {
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        while (userInput.isEmpty())
        {
            System.out.println("Ввод не может быть пустым! Повторите ввод:");
            userInput = input.nextLine();
        }
        return userInput;
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

    private static void printAllFurniture(ArrayList<Furniture> furnitureStack) {
        int count = 1;
        if (furnitureStack.isEmpty()) {
            System.out.println("Мебели нет!");
        }
        else {
            for(Furniture i: furnitureStack) {
                System.out.println("#" + count + " | Имя - " + i.getName() + " | Цвет - " + i.getColor() +
                        " | Длина - " + i.getLength() + " см | Цена - " + i.getPrice() + " ₽");
                count++;
            }
        }
    }

    private static Furniture getNewFurnDetails() {

        System.out.println("Введите имя мебели:");
        String furnitureName = getStringInput();

        System.out.println("Введите цвет мебели:");
        String furnitureColor = getStringInput();

        System.out.println("Введите длину мебели в сантиметрах:");
        int furnitureLength = getIntInput();
        while (furnitureLength < 1) {
            System.out.println("Цена должна быть больше 0! Введите новую длину: ");
            furnitureLength = getIntInput();
        }

        System.out.println("Введите (double)цену мебели:");
        double furniturePrice = getDoubleInput();


        return new Furniture(furnitureName, furnitureColor,furnitureLength, furniturePrice);
    }

    private static void editFurnVar(Furniture furnitureToEdit, int editOperation) {
        Scanner input = new Scanner(System.in);

        switch (editOperation) {
            case 1 -> {
                System.out.println("Введите новое имя:");
                String newName = input.nextLine();
                while (newName.isEmpty()) {
                    System.out.println("Имя не может быть пустым! Введите новое имя:");
                    newName = input.nextLine();
                }
                furnitureToEdit.setName(newName);
                System.out.println("Новое имя задано!");
            }
            case 2 -> {
                System.out.println("Введите новый цвет:");
                String newColor = input.nextLine();
                while (newColor.isEmpty()) {
                    System.out.println("Имя не может быть пустым! Введите новое имя:");
                    newColor = input.nextLine();
                }
                furnitureToEdit.setColor(newColor);
                System.out.println("Новый цвет задан!");
            }
            case 3 -> {
                System.out.println("Введите новую длину:");
                int newLength = getIntInput();
                while (newLength < 1) {
                    System.out.println("Длина должна быть больше 0! Введите новую длину:");
                    newLength = getIntInput();
                }
                furnitureToEdit.setLength(newLength);
                System.out.println("Новая длина задана!");
            }
            case 4 -> {
                System.out.println("Введите новую цену:");
                double newPrice = getDoubleInput();
                while (newPrice < 1) {
                    System.out.println("Цена должна быть больше 0! Введите новую цену:");
                    newPrice = getDoubleInput();
                }
                furnitureToEdit.setPrice(newPrice);
                System.out.println("Новая цена задана!");
            }
            case 5 -> System.out.println("отмена");
            default -> System.out.println("Данного пункта нет в меню!");
        }
    }

    private static ArrayList<Furniture> sortAllFurniture(ArrayList<Furniture> furnitureStack) {
        printSortMenu();

        System.out.println("Выберите по какому полю сортировать мебель:");
        int numToEdit = getIntInput();

        switch (numToEdit) {
            case 1 -> {
                }
            default -> System.out.println("Данного пункта нет в меню!");
        }
        return furnitureStack;
    }

    public static void main(String[] args) {

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

                    if (!furnitureStack.isEmpty()) {
                        System.out.println("Выберите какую мебель изменить:");
                        int numToEdit = getIntInput();

                        if (numToEdit < 1 || numToEdit > furnitureStack.size()) {
                            System.out.println("Выберите корректный номер мебели для изменения! (от 1 до " +
                                    furnitureStack.size() + ")");
                        }
                        else {
                            printEditMenu();
                            int editOperation = getIntInput();
                            editFurnVar(furnitureStack.get(numToEdit - 1), editOperation);
                        }
                    }
                }
                case 4 -> {
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
                case 5 -> {
                    if (!furnitureStack.isEmpty()) {
                        int count = 1;
                        for(Furniture i: furnitureStack) {
                            if (i.getLength() != null) {
                                System.out.println("#" + count + " | " + i.getName() + " | " + i.getShippingInfo());
                                count++;
                            }
                            else {
                                System.out.println("#" + count + " | " + i.getName() + " | Размеры мебели не заданы!");
                            }
                        }
                    }
                }
                case 6 -> {
                    printAllFurniture(furnitureStack);
                    if (!furnitureStack.isEmpty()) {
                            furnitureStack = sortAllFurniture(furnitureStack);
                        }
                }
                case 7 -> printAllFurniture(furnitureStack);
                case 8 -> System.out.println("До свидания!");
                default -> System.out.println("Данного пункта нет в меню!");
            }
        } while (menuChoice != 8);

    }
}