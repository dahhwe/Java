package org.hibernate.lab4;

import javax.persistence.*;

/**
 * Сущность представляет компанию в базе данных.
 */
@Entity
@Table(name = "Company")
public class Corporation {

    /**
     * Уникальный идентификатор компании, автоматически генерируемый.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CompanyID")
    private int companyId;

    /**
     * Название компании.
     */
    @Column(name = "Name")
    private String name;

    /**
     * Отрасль, в которой работает компания.
     */
    @Column(name = "Industry")
    private String industry;

    /**
     * Получает идентификатор компании.
     *
     * @return идентификатор компании.
     */
    public int getCompanyId() {
        return companyId;
    }

    /**
     * Задает идентификатор компании.
     *
     * @param companyId идентификатор компании для установки.
     */
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    /**
     * Получает название компании.
     *
     * @return название компании.
     */
    public String getName() {
        return name;
    }

    /**
     * Задает название компании.
     *
     * @param name название компании для установки.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получает название отрасли компании.
     *
     * @return название отрасли.
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * Задает отрасль компании.
     *
     * @param industry отрасль для установки.
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
