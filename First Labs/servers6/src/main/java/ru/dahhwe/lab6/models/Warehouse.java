package ru.dahhwe.lab6.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Класс-сущность, представляющий склад в базе данных.
 * Содержит информацию о складе, включая его идентификатор, принадлежность к компании, название, местоположение, а также связанные продукты и услуги.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WarehouseID")
    private int warehouseId;

    @ManyToOne
    @JoinColumn(name = "CompanyID")
    private Corporation company;

    @Column(name = "Name")
    private String name;

    @Column(name = "Location")
    private String location;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Service> services;
}
