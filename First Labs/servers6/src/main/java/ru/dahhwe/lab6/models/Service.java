package ru.dahhwe.lab6.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
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
}

