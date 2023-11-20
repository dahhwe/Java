package org.hibernate.lab5.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Класс-сущность, представляющий склад в базе данных.
 * Содержит информацию о складе, включая его идентификатор, принадлежность к компании, название, местоположение, а также связанные продукты и услуги.
 */
@Entity
@Table(name = "Warehouse")
public class Warehouse {

    /**
     * Конструктор склада с параметрами.
     *
     * @param warehouseId Идентификатор склада.
     * @param company     Компания, к которой принадлежит склад.
     * @param name        Название склада.
     * @param location    Местоположение склада.
     * @param products    Набор продуктов, хранящихся на складе.
     * @param services    Набор услуг, предоставляемых складом.
     */
    public Warehouse(int warehouseId, Company company, String name, String location, Set<Product> products, Set<Service> services) {
        this.warehouseId = warehouseId;
        this.company = company;
        this.name = name;
        this.location = location;
        this.products = products;
        this.services = services;
    }

    /**
     * Конструктор по умолчанию для склада.
     */
    public Warehouse() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WarehouseID")
    private int warehouseId;

    @ManyToOne
    @JoinColumn(name = "CompanyID")
    private Company company;

    @Column(name = "Name")
    private String name;

    @Column(name = "Location")
    private String location;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Service> services;

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }
}
