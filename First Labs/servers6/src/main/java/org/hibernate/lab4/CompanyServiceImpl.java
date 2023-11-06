package org.hibernate.lab4;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CompanyServiceImpl extends DAO implements CompanyService {

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
        Session session = getSession();
        Query query = session.createQuery("from Corporation where companyId = :id");
        query.setParameter("id", id);
        Corporation corporation = (Corporation) query.uniqueResult();
        closeSession();
        return corporation;
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
        Session session = getSession();
        Query query = session.createQuery("from Corporation");
        List<Corporation> companies = query.list();
        closeSession();
        return companies;
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
        Session session = getSession();
        String hql = "SELECT c.industry FROM Corporation c WHERE c.companyId BETWEEN :startId AND :endId";
        Query<String> query = session.createQuery(hql, String.class);
        query.setParameter("startId", startId);
        query.setParameter("endId", endId);
        List<String> industryNames = query.list();
        closeSession();
        return industryNames;
    }
}
