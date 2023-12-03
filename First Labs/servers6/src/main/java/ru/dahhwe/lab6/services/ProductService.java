package ru.dahhwe.lab6.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dahhwe.lab6.repositories.ProductRepository;

@Service
@Transactional(readOnly = true)
public class ProductService {
    ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }
}
