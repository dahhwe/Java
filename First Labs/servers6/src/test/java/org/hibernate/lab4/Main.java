package org.hibernate.lab4;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CompanyService companyService = new CompanyServiceImpl();

        // Создание новой компании
        Corporation newCorporation = new Corporation();
        newCorporation.setName("New Company");
        newCorporation.setIndustry("Technology");
        companyService.saveCompany(newCorporation);

        // Обновление компании
        newCorporation.setIndustry("Finance");
        companyService.updateCompany(newCorporation);

        // Поиск компании по id
        Corporation foundCorporation = companyService.findCompanyById(newCorporation.getCompanyId());
        System.out.println("Found company: " + foundCorporation.getName());

        // Получение списка всех компаний
        List<Corporation> allCompanies = companyService.findAllCompanies();
        for (Corporation corporation : allCompanies) {
            System.out.println("Company: " + corporation.getName());
        }

        // Удаление компании
        companyService.deleteCompany(newCorporation);
    }
}

