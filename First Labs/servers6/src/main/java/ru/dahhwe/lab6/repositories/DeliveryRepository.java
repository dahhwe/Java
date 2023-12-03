package ru.dahhwe.lab6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dahhwe.lab6.models.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

}
