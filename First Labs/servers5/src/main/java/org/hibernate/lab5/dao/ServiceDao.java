package org.hibernate.lab5.dao;

import org.hibernate.lab5.model.Service;

public class ServiceDao extends AbstractDao<Service, Integer> {

    public ServiceDao() {
        super(Service.class);
    }
}
