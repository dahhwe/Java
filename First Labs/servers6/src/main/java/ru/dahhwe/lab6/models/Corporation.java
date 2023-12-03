package ru.dahhwe.lab6.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность представляет компанию в базе данных.
 */
@Entity
@Data
@NoArgsConstructor
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
}
