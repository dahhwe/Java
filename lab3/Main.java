// Вариант 8
// Для каждого варианта имеется набор из четырех сущностей.
// Необходимо выстроить иерархию наследования.
// В каждом классе (базовом и производных) должно быть
// не менее одного числового и одного строкового поля.
// При вводе числовых параметров обязательна проверка на
// число и на диапазон (даже если число может быть любое, проверку необходимо реализовать).

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
                │            Главное Меню            │
                ╰────────────────────────────────────╯
                1 — Добавить новый двигатель
                2 — Удалить двигатель по индексу
                3 — Вывод всех двигателей
                4 — Сравнение двух двигателей на равенство (по индексам)
                5 — Завершение работы приложения \n \n""");
    }

    /**
     * Меню добавления двигателей.
     */
    private static void printEnginesMenu() {

        System.out.print("""
                ╭────────────────────────────────────╮
                │     Меню добавления двигателей     │
                ╰────────────────────────────────────╯
                1 — Добавить простой двигатель
                2 — Добавить двигатель внутреннего сгорания
                3 — Добавить дизельный двигатель
                4 — Добавить реактивный двигатель
                5 — Отмена \n \n""");
    }

    /**
     * Меню параметров.
     */
    private static void printConfigMenu() {
        System.out.print("""
                ╭────────────────────────────────────╮
                │          Меню параметров           │
                ╰────────────────────────────────────╯
                1 — Добавить двигатель с параметрами
                2 — Добавить двигатель без параметров
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

    /**
     * Функция предоставляет пользователю выбор добавить двигатель с параметрами или без.
     *
     * @param engineStack Массив с двигателями.
     */
    private static void enginesConfig(ArrayList<Engine> engineStack) {
        printConfigMenu();
        int choice = getIntInput();
        switch (choice) {
            case 1 -> getNewEngineDetails(engineStack);
            case 2 -> getNewClearEngine(engineStack);
            case 3 -> System.out.println("Отмена");
            default -> System.out.println("Данного пункта нет в меню!");
        }
    }

    /**
     * Функция предоставляет выбор добавить двигатель с выбранной пользователем конфигурацией.
     *
     * @param engineStack Массив с двигателями.
     */
    private static void getNewClearEngine(ArrayList<Engine> engineStack) {
        printEnginesMenu();
        int userChoice = getIntInput();
        switch (userChoice) {
            case 1 -> engineStack.add(createNewClearBasicEngine());
            case 2 -> engineStack.add(createNewClearIntCombustionEngine());
            case 3 -> engineStack.add(createNewClearDieselEngine());
            case 4 -> engineStack.add(createNewClearJetEngine());
            case 5 -> System.out.println("Отмена");
            default -> System.out.println("Данного пункта нет в меню!");
        }
    }

    /**
     * Создание обычного двигателя без параметров.
     *
     * @return обычный двигатель без параметров
     */
    private static Engine createNewClearBasicEngine() {
        return new Engine();
    }

    /**
     * Создание двигателя внутреннего сгорания без параметров.
     *
     * @return двигатель внутреннего сгорания без параметров.
     */
    private static Engine createNewClearIntCombustionEngine() {
        return new internalCombustionEngine();
    }

    /**
     * Создание дизельного двигателя внутреннего сгорания без параметров.
     *
     * @return дизельный двигатель внутреннего сгорания без параметров.
     */
    private static Engine createNewClearDieselEngine() {
        return new dieselEngine();
    }

    /**
     * Создание реактивного двигателя внутреннего сгорания без параметров.
     *
     * @return реактивный двигатель внутреннего сгорания без параметров.
     */
    private static Engine createNewClearJetEngine() {
        return new jetEngine();
    }

    /**
     * Функция предоставляет пользователю выбор двигателя, который после создания
     * будет добавлен массив с остальными двигателями.
     *
     * @param engineStack Массив с двигателями.
     */
    private static void getNewEngineDetails(ArrayList<Engine> engineStack) {
        printEnginesMenu();
        int userChoice = getIntInput();
        switch (userChoice) {
            case 1 -> engineStack.add(createNewBasicEngine());
            case 2 -> engineStack.add(createNewIntCombustionEngine());
            case 3 -> engineStack.add(createNewDieselEngine());
            case 4 -> engineStack.add(createNewJetEngine());
            case 5 -> System.out.println("Отмена");
            default -> System.out.println("Данного пункта нет в меню!");
        }
    }

    /**
     * Функция удаляет объект двигателя из массива по индексу.
     *
     * @param engineStack Массив с двигателями.
     */
    private static void deleteEngine(ArrayList<Engine> engineStack) {
        printAllEngines(engineStack);

        if (!engineStack.isEmpty()) {
            System.out.println("Введите номер двигателя для удаления");
            int numToDelete = getValidIndex(engineStack);
            engineStack.remove(numToDelete - 1);
            System.out.println("Двигатель #" + numToDelete + " удален!");
        }
    }

    /**
     * Функция выводит все информацию о всех двигателях в массиве.
     *
     * @param engineStack Массив с двигателями.
     */
    private static void printAllEngines(ArrayList<Engine> engineStack) {
        int count = 1;
        if (engineStack.isEmpty()) {
            System.out.println("Двигателей нет!");
        } else {
            for (Engine i : engineStack) {
                System.out.println("#" + count + i.toString());
                count++;
            }
        }
    }

    /**
     * Функция создает новый обычный двигатель.
     *
     * @return Созданный обычный двигатель.
     */
    private static Engine createNewBasicEngine() {
        System.out.println("Введите название двигателя:");
        String engineName = getStringInput();

        System.out.println("Введите мощность двигателя:");
        double enginePower = getDoubleInput();

        return new Engine(engineName, enginePower);
    }

    /**
     * Функция создает новый двигатель внутреннего сгорание.
     *
     * @return Созданный двигатель внутреннего сгорание.
     */
    private static Engine createNewIntCombustionEngine() {
        System.out.println("Введите название двигателя:");
        String engineName = getStringInput();

        System.out.println("Введите мощность двигателя:");
        double enginePower = getDoubleInput();

        System.out.println("Введите область использования:");
        String fieldOfUse = getStringInput();

        System.out.println("Введите детонационную стойкость бензина:");
        int fuelType = getIntInput();

        System.out.println("Введите часовой расход топлива:");
        double fuelConsumptionHourly = getDoubleInput();

        return new internalCombustionEngine(engineName, enginePower, fieldOfUse, fuelType, fuelConsumptionHourly);
    }

    /**
     * Функция создает новый дизельный двигатель.
     *
     * @return Созданный дизельный двигатель.
     */
    private static Engine createNewDieselEngine() {

        System.out.println("Введите название двигателя:");
        String engineName = getStringInput();

        System.out.println("Введите мощность двигателя:");
        double enginePower = getDoubleInput();

        System.out.println("Введите компанию производителя двигателя:");
        String engineManufacturer = getStringInput();

        System.out.println("Введите объем двигателя в литрах:");
        double engineDisplacement = getDoubleInput();

        return new dieselEngine(engineName, enginePower, engineManufacturer, engineDisplacement);
    }

    /**
     * Функция создает новый реактивный двигатель.
     *
     * @return Созданный реактивный двигатель.
     */
    private static Engine createNewJetEngine() {
        System.out.println("Введите название двигателя:");
        String engineName = getStringInput();

        System.out.println("Введите мощность двигателя:");
        double enginePower = getDoubleInput();

        System.out.println("Перечислите на каких самолетах устанавливается данный двигатель:");
        String jetsUse = getStringInput();

        System.out.println("Введите энергоэффективность двигателя:");
        double energyEfficiency = getDoubleInput();

        System.out.println("Введите тяговооружённость двигателя:");
        double thrustToWeightRatio = getDoubleInput();

        System.out.println("Введите максимальную скорость самолета с данным двигателем:");
        int maxSpeed = getIntInput();

        return new jetEngine(engineName, enginePower, jetsUse, energyEfficiency, thrustToWeightRatio, maxSpeed);
    }

    /**
     * Функция получает корректный ввод индекса элемента массива от пользователя.
     *
     * @param engineStack Массив
     * @return Индекс элемента массива.
     */
    private static int getValidIndex(ArrayList<Engine> engineStack) {
        int index = getIntInput();
        while (index < 1 || index > engineStack.size()) {
            System.out.println("Введите корректный индекс! (от 1 до " +
                    engineStack.size() + "):");
            index = getIntInput();
        }
        return index;
    }

    /**
     * Функция проверяет два объекта на равенство по индексам.
     *
     * @param engineStack Массив двигателей
     */
    private static void compareEngines(ArrayList<Engine> engineStack) {
        printAllEngines(engineStack);

        System.out.println("Введите номер первого двигателя для сравнения:");
        int firstEngineIndex = getValidIndex(engineStack) - 1;

        System.out.println("Введите номер второго двигателя для сравнения:");
        int secondEngineIndex = getValidIndex(engineStack) - 1;

        if (!(secondEngineIndex == firstEngineIndex)) {
            if (engineStack.get(firstEngineIndex).hashCode() == engineStack.get(secondEngineIndex).hashCode()) {
                System.out.println("хэш-коды двух объектов равны!");
                if (engineStack.get(firstEngineIndex).equals(engineStack.get(secondEngineIndex))) {
                    System.out.println("Объекты равны, так как хэш-коды и операция равенства двух объектов равны");
                } else {
                    System.out.println("Объекты не равны, так как операция равенства двух объектов не равна");
                }
            } else {
                System.out.println("Объекты не равны, так как хэш-коды двух объектов не равны");
            }
        } else {
            System.out.println("Нельзя сравнивать одинаковые индексы!");
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

        ArrayList<Engine> engineStack = new ArrayList<>();
        int menuChoice;

        do {
            printMenu();
            System.out.println("Введите Ваш выбор:");
            menuChoice = getIntInput();

            switch (menuChoice) {
                case 1 -> enginesConfig(engineStack);
                case 2 -> deleteEngine(engineStack);
                case 3 -> printAllEngines(engineStack);
                case 4 -> compareEngines(engineStack);
                case 5 -> System.out.println("До свидания!");
                default -> System.out.println("Данного пункта нет в меню!");
            }
        } while (menuChoice != 5);
    }
}
