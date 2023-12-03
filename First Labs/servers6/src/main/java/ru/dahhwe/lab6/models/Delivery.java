package ru.dahhwe.lab6.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DeliveryID")
    private int deliveryId;

    @OneToOne
    @JoinColumn(name = "ProductID")
    private Product product;

    @Column(name = "Addressee")
    private String addressee;

    @Column(name = "Address")
    private String address;
}
