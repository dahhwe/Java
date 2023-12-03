package ru.dahhwe.lab6.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private int productId;

    @ManyToOne
    @JoinColumn(name = "WarehouseID")
    private Warehouse warehouse;

    @Column(name = "Name")
    private String name;

    @Column(name = "Value")
    private BigDecimal value;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Delivery delivery;
}
