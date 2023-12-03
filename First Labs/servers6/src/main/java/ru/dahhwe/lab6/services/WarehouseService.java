package ru.dahhwe.lab6.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dahhwe.lab6.models.Corporation;
import ru.dahhwe.lab6.repositories.WarehouseRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class WarehouseService {
    WarehouseRepository repository;

    @Autowired
    public WarehouseService(WarehouseRepository repository) {
        this.repository = repository;
    }
}
