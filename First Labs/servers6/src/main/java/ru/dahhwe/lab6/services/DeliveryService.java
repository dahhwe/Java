package ru.dahhwe.lab6.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dahhwe.lab6.repositories.DeliveryRepository;

@Service
@Transactional(readOnly = true)
public class DeliveryService {
    DeliveryRepository repository;

    @Autowired
    public DeliveryService(DeliveryRepository repository) {
        this.repository = repository;
    }
}
