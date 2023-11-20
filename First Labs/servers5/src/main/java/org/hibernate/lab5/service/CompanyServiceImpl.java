package org.hibernate.lab5.service;

import org.hibernate.Session;
import org.hibernate.lab5.dao.CompanyDao;
import org.hibernate.lab5.model.Corporation;
import org.hibernate.lab5.dao.DAO;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CompanyServiceImpl extends DAO implements CompanyService {

    private final CompanyDao companyDao;

    public CompanyServiceImpl() {
        this.companyDao = new CompanyDao();
    }

    /**
     * Сохраняет объект Corporation в базу данных.
     *
     * @param corporation объект корпорации для сохранения
     */
    @Override
    public void saveCompany(Corporation corporation) {
        Session session = getSession();
        begin();
        session.save(corporation);
        commit();
        closeSession();
    }

    /**
     * Обновляет существующую запись корпорации в базе данных.
     *
     * @param corporation объект корпорации для обновления
     */
    @Override
    public void updateCompany(Corporation corporation) {
        Session session = getSession();
        begin();
        session.update(corporation);
        commit();
        closeSession();
    }

    /**
     * Находит корпорацию по её идентификатору.
     *
     * @param id идентификатор корпорации
     * @return объект корпорации или null, если корпорация не найдена
     */
    @Override
    public Corporation findCompanyById(int id) {
        try (Session session = getSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Corporation> cq = cb.createQuery(Corporation.class);
            Root<Corporation> root = cq.from(Corporation.class);
            cq.where(cb.equal(root.get("companyId"), id));
            return session.createQuery(cq).uniqueResult();
        } finally {
            closeSession();
        }
    }


    /**
     * Удаляет корпорацию из базы данных.
     *
     * @param corporation объект корпорации для удаления
     */
    @Override
    public void deleteCompany(Corporation corporation) {
        Session session = getSession();
        begin();
        session.delete(corporation);
        commit();
        closeSession();
    }

    /**
     * Возвращает список всех корпораций в базе данных.
     *
     * @return список корпораций
     */
    @Override
    public List<Corporation> findAllCompanies() {
        try (Session session = getSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Corporation> cq = cb.createQuery(Corporation.class);
            Root<Corporation> root = cq.from(Corporation.class);
            cq.select(root);
            return session.createQuery(cq).getResultList();
        } finally {
            closeSession();
        }
    }


    /**
     * Получает название корпорации по её идентификатору.
     *
     * @param id идентификатор корпорации
     * @return название корпорации или null, если корпорация не найдена
     */
    @Override
    public String getName(int id) {
        Session session = getSession();
        begin();
        Corporation corporation = session.get(Corporation.class, id);
        commit();
        closeSession();
        return corporation != null ? corporation.getName() : null;
    }

    /**
     * Возвращает названия отраслей для корпораций в заданном диапазоне идентификаторов.
     *
     * @param startId начальный идентификатор диапазона
     * @param endId   конечный идентификатор диапазона
     * @return список названий отраслей
     */
    @Override
    public List<String> getIndustryNamesInRange(int startId, int endId) {
        try (Session session = getSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<String> cq = cb.createQuery(String.class);
            Root<Corporation> root = cq.from(Corporation.class);
            cq.select(root.get("industry")).where(cb.between(root.get("companyId"), startId, endId));
            return session.createQuery(cq).getResultList();
        } finally {
            closeSession();
        }
    }

    @Override
    public List<Corporation> findCorporationsWithFilters(String name, String industry) {
        return companyDao.findCorporationsWithDynamicFilter(name, industry);
    }
}
