package org.hibernate.lab5.model;

import javax.persistence.*;

@Entity
@Table(name = "Service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ServiceID")
    private int serviceId;

    @ManyToOne
    @JoinColumn(name = "WarehouseID")
    private Warehouse warehouse;

    @Column(name = "ServiceName")
    private String serviceName;

    @Column(name = "Requirements")
    private String requirements;

    // Конструкторы, геттеры и сеттеры
}

