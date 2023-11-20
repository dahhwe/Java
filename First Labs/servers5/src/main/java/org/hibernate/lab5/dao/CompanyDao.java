package org.hibernate.lab5.dao;

import org.hibernate.Session;
import org.hibernate.lab5.model.Corporation;
import org.hibernate.lab5.util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO-класс для работы с объектами Corporation.
 * Расширяет функциональность абстрактного класса AbstractDao для сущности Corporation.
 */
public class CompanyDao extends AbstractDao<Corporation, Integer> {

    /**
     * Конструктор класса CompanyDao.
     * Инициализирует новый экземпляр DAO для сущности Corporation.
     */
    public CompanyDao() {
        super(Corporation.class);
    }

    /**
     * Ищет корпорации, используя динамически формируемые критерии фильтрации.
     *
     * @param name     Название корпорации для фильтрации или null, если фильтрация по названию не требуется.
     * @param industry Отрасль для фильтрации или null, если фильтрация по отрасли не требуется.
     * @return Список корпораций, соответствующих заданным критериям фильтрации.
     */
    public List<Corporation> findCorporationsWithDynamicFilter(String name, String industry) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Corporation> cq = cb.createQuery(Corporation.class);
            Root<Corporation> root = cq.from(Corporation.class);

            List<Predicate> predicates = new ArrayList<>();
            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (industry != null && !industry.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("industry")), "%" + industry.toLowerCase() + "%"));
            }
            cq.where(predicates.toArray(new Predicate[0]));

            return session.createQuery(cq).getResultList();
        }
    }

}
