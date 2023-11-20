package org.hibernate.lab5.application;

import org.hibernate.lab5.dao.ProductDao;
import org.hibernate.lab5.model.Corporation;
import org.hibernate.lab5.model.Product;
import org.hibernate.lab5.service.CompanyService;
import org.hibernate.lab5.service.CompanyServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleApplication {

    private static final Scanner scanner = new Scanner(System.in);
    private static final CompanyService companyService = new CompanyServiceImpl();
    private static final ProductDao productDao = new ProductDao();

    /**
     * Запускает консольное приложение и отображает пользовательское меню.
     */
    public static void start() {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера сканера от символа новой строки

            switch (choice) {
                case 1 -> executeGetName();
                case 2 -> executeGetValueRange();
                case 3 -> executeCreateNewRecord();
                case 4 -> executeFindCorporationsWithFilters();
                case 5 -> executeDeleteCompany();
                case 6 -> displayProductsAndDeliveries();
                case 0 -> System.exit(0);
                default -> System.out.println("Неверный выбор. Пожалуйста, попробуйте ещё раз.");
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
        System.out.println("4. Найти компании по фильтрам");
        System.out.println("5. Удалить компанию");
        System.out.println("6. Показать информацию о продуктах и доставках");
        System.out.println("0. Выход");
        System.out.print("Введите ваш выбор: ");
    }


    /**
     * Выполняет операцию получения информации о компании по её идентификатору.
     */
    private static void executeGetName() {
        System.out.print("Введите ID компании: ");
        String input = scanner.nextLine();

        try {
            int id = Integer.parseInt(input);
            Corporation corporation = companyService.findCompanyById(id);

            if (corporation != null) {
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
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод. Пожалуйста, введите числовой ID.");
        }
    }


    /**
     * Выполняет операцию получения названий отраслей компаний в заданном диапазоне идентификаторов.
     */
    private static void executeGetValueRange() {
        try {
            System.out.print("Введите начальный ID: ");
            String startInput = scanner.nextLine();
            int startId = Integer.parseInt(startInput);

            System.out.print("Введите конечный ID: ");
            String endInput = scanner.nextLine();
            int endId = Integer.parseInt(endInput);

            List<String> industryNames = companyService.getIndustryNamesInRange(startId, endId);
            if (industryNames.isEmpty()) {
                System.out.println("Отрасли не найдены в данном диапазоне ID.");
            } else {
                System.out.println("Названия отраслей:");
                for (String industryName : industryNames) {
                    System.out.println(industryName);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод. Пожалуйста, введите числовые значения для ID.");
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
        String input = scanner.nextLine();

        try {
            int id = Integer.parseInt(input);
            Corporation corporation = companyService.findCompanyById(id);
            if (corporation != null) {
                companyService.deleteCompany(corporation);
                System.out.println("Компания с ID " + id + " успешно удалена.");
            } else {
                System.out.println("Компания с таким ID не найдена.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод. Пожалуйста, введите числовой ID.");
        }
    }


    private static void executeFindCorporationsWithFilters() {
        System.out.print("Введите имя компании (или оставьте пустым для пропуска): ");
        String name = scanner.nextLine().trim();

        System.out.print("Введите отрасль компании (или оставьте пустым для пропуска): ");
        String industry = scanner.nextLine().trim();

        List<Corporation> corporations = companyService.findCorporationsWithFilters(name, industry);
        if (corporations.isEmpty()) {
            System.out.println("Компании не найдены.");
        } else {
            System.out.println("+------------+--------------------------------+-----------------+");
            System.out.printf("| ID компании | %-30s | Отрасль          |\n", "Название");
            System.out.println("+------------+--------------------------------+-----------------+");
            for (Corporation corporation : corporations) {
                System.out.printf("| %-11d | %-30s | %-15s |\n",
                        corporation.getCompanyId(),
                        corporation.getName(),
                        corporation.getIndustry());
            }
            System.out.println("+------------+--------------------------------+-----------------+");
        }
    }

    // В ConsoleApplication
    private static void displayProductsAndDeliveries() {
        System.out.println("Введите ID продукта (оставьте пустым, чтобы пропустить): ");
        String productIdInput = scanner.nextLine();
        Optional<Integer> productIdOpt = Optional.empty();
        if (!productIdInput.trim().isEmpty()) {
            productIdOpt = Optional.of(Integer.parseInt(productIdInput));
        }

        System.out.println("Введите адрес доставки (оставьте пустым, чтобы пропустить): ");
        String deliveryAddressInput = scanner.nextLine();
        Optional<String> deliveryAddressOpt = Optional.empty();
        if (!deliveryAddressInput.trim().isEmpty()) {
            deliveryAddressOpt = Optional.of(deliveryAddressInput);
        }

        List<Object[]> productsWithDeliveryInfo = productDao.findProductsWithDeliveryInfo(productIdOpt, deliveryAddressOpt);
        System.out.println("Найдено компаний: " + productsWithDeliveryInfo.size());
        for (Object[] row : productsWithDeliveryInfo) {
            Product product = (Product) row[0];
            String deliveryAddress = (String) row[1];

            System.out.printf("Product ID: %d, Name: %s, Delivery Address: %s%n",
                    product.getProductId(), product.getName(), deliveryAddress);
        }
    }

}
