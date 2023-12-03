package ru.dahhwe.lab6.repositories;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dahhwe.lab6.models.Corporation;

@Repository
public interface CorporationRepository extends JpaRepository<Corporation, Integer> {

}
