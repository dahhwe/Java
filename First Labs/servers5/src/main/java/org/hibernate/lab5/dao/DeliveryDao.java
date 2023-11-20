package org.hibernate.lab5.dao;

import org.hibernate.lab5.model.Delivery;

public class DeliveryDao extends AbstractDao<Delivery, Integer> {

    public DeliveryDao() {
        super(Delivery.class);
    }
}
