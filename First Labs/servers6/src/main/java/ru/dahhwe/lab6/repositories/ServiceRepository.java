package ru.dahhwe.lab6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dahhwe.lab6.models.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {

}

