package ru.dahhwe.lab6.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dahhwe.lab6.repositories.ServiceRepository;

@Service
@Transactional(readOnly = true)
public class ServiceService {
    ServiceRepository repository;

    @Autowired
    public ServiceService(ServiceRepository repository) {
        this.repository = repository;
    }
}
