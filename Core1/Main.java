// Вариант 8
// Для каждого варианта имеется набор из четырех сущностей.
// Необходимо выстроить иерархию наследования.
// В каждом классе (базовом и производных) должно быть
// не менее одного числового и одного строкового поля.
// При вводе числовых параметров обязательна проверка на
// число и на диапазон (даже если число может быть любое, проверку необходимо реализовать).

import java.util.*;

/**
 * Класс Main используется для управления объектами ЭВМ.
 */
public class Main {

    /**
     * Минимальная мощность ЭВМ в Терафлопс
     */
    private static final double MIN_EVM_POWER = 1.0;
    /**
     * Максимальная мощность ЭВМ в Терафлопс
     */
    private static final double MAX_EVM_POWER = 100000.0;
    /**
     * Минимальный размер экрана ЭВМ в дюймах
     */
    private static final double MIN_SCREEN_SIZE = 1.0;
    /**
     * Максимальный размер экрана ЭВМ в дюймах
     */
    private static final double MAX_SCREEN_SIZE = 100.0;


    /**
     * Основное меню программы.
     */
    private static void printMenu() {
        System.out.print("""
                ╭────────────────────────────────────╮
                │            Главное Меню            │
                ╰────────────────────────────────────╯
                1 — Добавить новую ЭВМ
                2 — Удалить ЭВМ по индексу
                3 — Вывод всех ЭВМ
                4 — Сравнение двух ЭВМ на равенство (по индексам)
                5 — Выход \n \n""");
    }

    /**
     * Меню добавления ЭВМ.
     */
    private static void printEnginesMenu() {
        System.out.print("""
                ╭────────────────────────────────────╮
                │         Меню добавления ЭВМ        │
                ╰────────────────────────────────────╯
                1 — Добавить Персональный компьютер
                2 — Добавить Ноутбук
                3 — Добавить Планшет
                4 — Отмена \n \n""");
    }

    /**
     * Меню параметров.
     */
    private static void printConfigMenu() {
        System.out.print("""
                ╭────────────────────────────────────╮
                │          Меню параметров           │
                ╰────────────────────────────────────╯
                1 — Добавить ЭВМ с параметрами
                2 — Добавить ЭВМ без параметров
                3 — Отмена \n \n""");
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
    private static String getStringInput() {

        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        while (userInput.isEmpty()) {
            System.out.println("Ввод не может быть пустым! Повторите ввод:");
            userInput = input.nextLine();
        }
        return userInput;
    }

    /**
     * Функция для получения целочисленного ввода в заданном диапазоне от пользователя.
     *
     * @param lowerLimit Нижний предел.
     * @param upperLimit Верхний предел.
     * @return Целочисленное число в заданном диапазоне.
     */
    public static int getIntInputWithParams(int lowerLimit, int upperLimit) {

        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        int userInt = 0;
        boolean allowedInput = false;

        do {
            try {
                userInt = Integer.parseInt(userInput);
                if (userInt < lowerLimit || userInt > upperLimit) {
                    System.out.print("Число не в заданном диапазоне! введите число " +
                            "(от " + lowerLimit + " до " + upperLimit + " )");
                    userInput = input.nextLine();
                } else {
                    allowedInput = true;
                }
            } catch (NumberFormatException ex) {
                System.out.print(" Неверный ввод. Введите число: ");
                userInput = input.nextLine();
            }
        } while (!allowedInput);
        return userInt;
    }

    private static double getDoubleInputWithParams(double lowerLimit, double upperLimit) {

        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        double userDouble = 0.0;
        boolean allowedInput = false;

        do {
            try {
                userDouble = Double.parseDouble(userInput);
                if (userDouble < lowerLimit || userDouble > upperLimit) {
                    System.out.print("Число не в заданном диапазоне! введите число " +
                            "(от " + lowerLimit + " до " + upperLimit + ")");
                    userInput = input.nextLine();
                } else {
                    allowedInput = true;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Некорректный ввод! Введите вещественное число:");
                userInput = input.nextLine();
            }
        } while (!allowedInput);
        return userDouble;
    }

    /**
     * Функция предоставляет пользователю выбор добавить ЭВМ с параметрами или без.
     *
     * @param MachinesList Массив с ЭВМ.
     */
    private static void makeMachine(ArrayList<ElectronicVirtualMachine> MachinesList) {

        printConfigMenu();
        int choice = getIntInput();
        switch (choice) {
            case 1 -> makeMachineWithDetails(MachinesList);
            case 2 -> makeClearMachine(MachinesList);
            case 3 -> System.out.println("Отмена");
            default -> System.out.println("Данного пункта нет в меню!");
        }
    }

    /**
     * Функция предоставляет выбор добавить ЭВМ с выбранной пользователем конфигурацией.
     *
     * @param MachinesList Массив с ЭВМ.
     */
    private static void makeClearMachine(ArrayList<ElectronicVirtualMachine> MachinesList) {

        printEnginesMenu();
        int userChoice = getIntInput();
        switch (userChoice) {
            case 1 -> MachinesList.add(new ElectronicVirtualMachine());
            case 2 -> MachinesList.add(new Notebook());
            case 3 -> MachinesList.add(new Tablet());
            case 4 -> System.out.println("Отмена");
            default -> System.out.println("Данного пункта нет в меню!");
        }
    }

    /**
     * Функция предоставляет пользователю выбор ЭВМ, которая после создания
     * будет добавлена в массив со всеми ЭВМ.
     *
     * @param MachinesList Массив с ЭВМ.
     */
    private static void makeMachineWithDetails(ArrayList<ElectronicVirtualMachine> MachinesList) {

        printEnginesMenu();
        int userChoice = getIntInput();
        switch (userChoice) {
            case 1 -> MachinesList.add(createNewBasicMachine());
            case 2 -> MachinesList.add(createNewNotebook());
            case 3 -> MachinesList.add(createNewTablet());
            case 4 -> System.out.println("Отмена");
            default -> System.out.println("Данного пункта нет в меню!");
        }
    }

    /**
     * Функция возвращает название ЭВМ.
     *
     * @return Название ЭВМ.
     */
    private static String getMachineName() {

        System.out.println("Введите название ЭВМ:");
        return getStringInput();
    }

    /**
     * Функция возвращает мощность ЭВМ в Терафлопс.
     *
     * @return Мощность ЭВМ в Терафлопс.
     */
    private static double getMachinePower() {

        System.out.println("Введите мощность ЭВМ в Терафлопс (от 1.0 до 100,000.0):");
        return getDoubleInputWithParams(MIN_EVM_POWER, MAX_EVM_POWER);
    }

    /**
     * Функция возвращает размер экрана ЭВМ.
     *
     * @return Размер экрана ЭВМ.
     */
    private static double getMachineScreenSize() {
        System.out.println("Введите размер экрана ЭВМ в дюймах (от 1.0 до 100.0):");
        return getDoubleInputWithParams(MIN_SCREEN_SIZE, MAX_SCREEN_SIZE);
    }


    /**
     * Функция удаляет объект ЭВМ из массива по индексу.
     *
     * @param engineStack Массив с ЭВМ.
     */
    private static void deleteMachine(ArrayList<ElectronicVirtualMachine> engineStack) {

        printAllMachines(engineStack);
        if (!engineStack.isEmpty()) {
            System.out.println("Введите номер ЭВМ для удаления:");
            int numToDelete = getValidIndex(engineStack);
            engineStack.remove(numToDelete - 1);
            System.out.println("ЭВМ #" + numToDelete + " удалена!");
        }
    }

    /**
     * Функция выводит все информацию о всех ЭВМ в массиве.
     *
     * @param engineStack Массив с ЭВМ.
     */
    private static void printAllMachines(ArrayList<ElectronicVirtualMachine> engineStack) {

        int count = 1;
        if (engineStack.isEmpty()) {
            System.out.println("ЭВМ нет!");
        } else {
            for (ElectronicVirtualMachine i : engineStack) {
                System.out.println("#" + count + i.toString());
                count++;
            }
        }
    }

    /**
     * Функция создает новую обычную ЭВМ.
     *
     * @return Созданная обычная ЭВМ.
     */
    private static ElectronicVirtualMachine createNewBasicMachine() {

        String engineName = getMachineName();
        double enginePower = getMachinePower();
        double screenSize = getMachineScreenSize();

        System.out.println("ЭВМ создана!");
        return new ElectronicVirtualMachine(engineName, enginePower, screenSize);
    }

    /**
     * Функция создает новый ноутбук.
     *
     * @return Созданный ноутбук.
     */
    private static ElectronicVirtualMachine createNewNotebook() {

        String machineName = getMachineName();
        double enginePower = getMachinePower();

        System.out.println("Введите название процессора:");
        String processor = getStringInput();

        System.out.println("Введите тактовую частоту процессора в ГГц (от 1 до 20):");
        int fuelType = getIntInputWithParams(1, 20);

        System.out.println("Ноутбук создан!");
        return new Notebook(machineName, enginePower, processor, fuelType);
    }

    /**
     * Функция создает новый объект ноутбука.
     *
     * @return Объект ноутбука.
     */
    private static ElectronicVirtualMachine createNewTablet() {

        String tabletName = getMachineName();
        double tabletPower = getMachinePower();

        System.out.println("Введите модель планшета:");
        String tabletModel = getStringInput();

        System.out.println("Введите емкость аккумулятора мАч (от 1000.0 до 100,000.0):");
        double batteryCapacity = getDoubleInputWithParams(1, 100000);

        System.out.println("Планшет создан!");
        return new Tablet(tabletName, tabletPower, tabletModel, batteryCapacity);
    }

    /**
     * Функция получает корректный ввод индекса элемента массива от пользователя.
     *
     * @param MachinesList Массив
     * @return Индекс элемента массива.
     */
    private static int getValidIndex(ArrayList<ElectronicVirtualMachine> MachinesList) {
        int index = getIntInput();
        while (index < 1 || index > MachinesList.size()) {
            System.out.println("Введите корректный индекс! (от 1 до " +
                    MachinesList.size() + "):");
            index = getIntInput();
        }
        return index;
    }

    /**
     * Функция проверяет два объекта на равенство по индексам.
     *
     * @param MachinesList Массив ЭВМ
     */
    private static void compareMachines(ArrayList<ElectronicVirtualMachine> MachinesList) {

        printAllMachines(MachinesList);
        if (!MachinesList.isEmpty()) {
            System.out.println("Введите номер первой ЭВМ для сравнения:");
            int firstMachineIndex = getValidIndex(MachinesList) - 1;

            System.out.println("Введите номер второй ЭВМ для сравнения:");
            int secondMachineIndex = getValidIndex(MachinesList) - 1;

            if (!(secondMachineIndex == firstMachineIndex)) {
                if (MachinesList.get(firstMachineIndex).equals(MachinesList.get(secondMachineIndex))) {
                    System.out.println("Объекты равны, так как хэш-коды и операция равенства двух объектов равны");
                } else {
                    System.out.println("Объекты не равны, так как операция равенства двух объектов не равна");
                }
            } else {
                System.out.println("Нельзя сравнивать одинаковые индексы!");
            }
        }
    }

    /**
     * Главная функция программы с возможностью вывода информации и
     * реализацией алгоритма.
     *
     * @param args массив последовательностей символов (строк),
     *             которые передаются в функцию main.
     */
    public static void main(String[] args) {

        ArrayList<ElectronicVirtualMachine> MachinesList = new ArrayList<>();
        int menuChoice;

        do {
            printMenu();
            System.out.println("Введите Ваш выбор:");
            menuChoice = getIntInput();

            switch (menuChoice) {
                case 1 -> makeMachine(MachinesList);
                case 2 -> deleteMachine(MachinesList);
                case 3 -> printAllMachines(MachinesList);
                case 4 -> compareMachines(MachinesList);
                case 5 -> System.out.println("До свидания!");
                default -> System.out.println("Данного пункта нет в меню!");
            }
        } while (menuChoice != 5);
    }
}
