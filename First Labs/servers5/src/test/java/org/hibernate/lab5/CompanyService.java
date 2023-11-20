package org.hibernate.lab5;

import org.hibernate.lab5.model.Corporation;

import java.util.List;

public interface CompanyService {
    void saveCompany(Corporation corporation);

    void updateCompany(Corporation corporation);

    Corporation findCompanyById(int id);

    void deleteCompany(Corporation corporation);

    List<Corporation> findAllCompanies();

    String getName(int id);
}
