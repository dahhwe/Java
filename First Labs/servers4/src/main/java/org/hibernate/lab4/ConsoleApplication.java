package org.hibernate.lab4;

import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {

    private static final CompanyService companyService = new CompanyServiceImpl();
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Запускает консольное приложение и отображает пользовательское меню.
     */
    public static void start() {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера сканера от символа новой строки

            switch (choice) {
                case 1:
                    executeGetName();
                    break;
                case 2:
                    executeGetValueRange();
                    break;
                case 3:
                    executeCreateNewRecord();
                    break;
                case 4:
                    executeDeleteCompany();
                    break;
                case 0:
                    System.exit(0); // Выход из программы
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте ещё раз.");
            }
        }
    }

    /**
     * Отображает главное меню приложения.
     */
    private static void displayMenu() {
        System.out.println("+-------------------------------------+");
        System.out.println("|            Базы данных              |");
        System.out.println("+-------------------------------------+");
        System.out.println("1. Получить название компании по ID");
        System.out.println("2. Получить названия отраслей по диапазону ID");
        System.out.println("3. Создать новую запись о компании");
        System.out.println("4. Удалить компанию");
        System.out.println("0. Выход");
        System.out.print("Введите ваш выбор: ");
    }


    /**
     * Выполняет операцию получения информации о компании по её идентификатору.
     */
    private static void executeGetName() {
        System.out.print("Введите ID компании: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера сканера

        Corporation corporation = companyService.findCompanyById(id);

        // Проверяем, что компания существует
        if (corporation != null) {
            // Выводим информацию о компании в виде таблицы
            System.out.println("+-----------------+--------------------------------+");
            System.out.println("| Поле            | Значение                       |");
            System.out.println("+-----------------+--------------------------------+");
            System.out.printf("| ID компании     | %d%n", corporation.getCompanyId());
            System.out.printf("| Название        | %s%n", corporation.getName());
            System.out.printf("| Отрасль         | %s%n", corporation.getIndustry());
            System.out.println("+-----------------+--------------------------------+");
        } else {
            System.out.println("Компания с ID " + id + " не найдена.");
        }
    }


    /**
     * Выполняет операцию получения названий отраслей компаний в заданном диапазоне идентификаторов.
     */
    private static void executeGetValueRange() {
        System.out.print("Введите начальный ID: ");
        int startId = scanner.nextInt();
        System.out.print("Введите конечный ID: ");
        int endId = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера сканера

        List<String> industryNames = companyService.getIndustryNamesInRange(startId, endId);
        System.out.println("Названия отраслей:");
        for (String industryName : industryNames) {
            System.out.println(industryName);
        }
    }

    /**
     * Выполняет операцию создания новой записи компании.
     */
    private static void executeCreateNewRecord() {
        System.out.print("Введите название компании: ");
        String name = scanner.nextLine().trim();
        System.out.print("Введите отрасль компании: ");
        String industry = scanner.nextLine().trim();

        if (name.isEmpty() || industry.isEmpty()) {
            System.out.println("Название и отрасль компании не могут быть пустыми. Пожалуйста, введите действительные значения.");
            return;
        }

        Corporation newCorporation = new Corporation();
        newCorporation.setName(name);
        newCorporation.setIndustry(industry);

        try {
            companyService.saveCompany(newCorporation);
            System.out.println("Новая запись о компании создана с ID: " + newCorporation.getCompanyId());
        } catch (Exception e) {
            System.out.println("Произошла ошибка при создании компании: " + e.getMessage());
        }
    }

    /**
     * Удаление компании по ID
     */
    private static void executeDeleteCompany() {
        System.out.print("Введите ID компании для удаления: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера сканера

        Corporation corporation = companyService.findCompanyById(id);
        if (corporation != null) {
            companyService.deleteCompany(corporation);
            System.out.println("Компания с ID " + id + " успешно удалена.");
        } else {
            System.out.println("Компания с таким ID не найдена.");
        }
    }

}
