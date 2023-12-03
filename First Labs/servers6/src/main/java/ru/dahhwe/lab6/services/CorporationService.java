package ru.dahhwe.lab6.services;

import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dahhwe.lab6.models.Corporation;
import ru.dahhwe.lab6.repositories.CorporationRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CorporationService {
    CorporationRepository repository;

    @Autowired
    public CorporationService(CorporationRepository repository) {
        this.repository = repository;
    }

    public List<Corporation> getAll() {
        return repository.findAll();
    }

    public Corporation getById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
