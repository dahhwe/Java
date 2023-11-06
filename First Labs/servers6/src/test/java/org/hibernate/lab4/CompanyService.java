package org.hibernate.lab4;

import java.util.List;

public interface CompanyService {
    void saveCompany(Corporation corporation);

    void updateCompany(Corporation corporation);

    Corporation findCompanyById(int id);

    void deleteCompany(Corporation corporation);

    List<Corporation> findAllCompanies();

    String getName(int id);
}
