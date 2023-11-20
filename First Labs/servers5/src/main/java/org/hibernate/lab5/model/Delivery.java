package org.hibernate.lab5.model;


import javax.persistence.*;

@Entity
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

    // Конструкторы, геттеры и сеттеры
}
