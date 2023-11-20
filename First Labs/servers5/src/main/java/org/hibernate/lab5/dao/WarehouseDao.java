package org.hibernate.lab5.dao;

import org.hibernate.lab5.model.Warehouse;

public class WarehouseDao extends AbstractDao<Warehouse, Integer> {

    public WarehouseDao() {
        super(Warehouse.class);
    }
}
