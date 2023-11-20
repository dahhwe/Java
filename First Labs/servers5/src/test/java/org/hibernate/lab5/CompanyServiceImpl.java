package org.hibernate.lab5;

import org.hibernate.Session;
import org.hibernate.lab5.dao.DAO;
import org.hibernate.lab5.model.Corporation;
import org.hibernate.lab5.service.CompanyService;
import org.hibernate.query.Query;

import java.util.List;

public class CompanyServiceImpl extends DAO implements CompanyService {

    @Override
    public void saveCompany(Corporation corporation) {
        Session session = getSession();
        begin();
        session.save(corporation);
        commit();
        closeSession();
    }

    @Override
    public void updateCompany(Corporation corporation) {
        Session session = getSession();
        begin();
        session.update(corporation);
        commit();
        closeSession();
    }

    @Override
    public Corporation findCompanyById(int id) {
        Session session = getSession();
        Query query = session.createQuery("from Company where companyId = :id");
        query.setParameter("id", id);
        Corporation corporation = (Corporation) query.uniqueResult();
        closeSession();
        return corporation;
    }

    @Override
    public void deleteCompany(Corporation corporation) {
        Session session = getSession();
        begin();
        session.delete(corporation);
        commit();
        closeSession();
    }

    @Override
    public List<Corporation> findAllCompanies() {
        Session session = getSession();
        Query query = session.createQuery("from Company");
        List<Corporation> companies = query.list();
        closeSession();
        return companies;
    }

    // New Method: getName
    @Override
    public String getName(int id) {
        Session session = getSession();
        begin();
        Corporation corporation = session.get(Corporation.class, id);
        commit();
        closeSession();
        return corporation != null ? corporation.getName() : null;
    }


    @Override
    public List<String> getIndustryNamesInRange(int startId, int endId) {
        Session session = getSession();
        String hql = "SELECT c.industry FROM Company c WHERE c.companyId BETWEEN :startId AND :endId";
        Query<String> query = session.createQuery(hql, String.class);
        query.setParameter("startId", startId);
        query.setParameter("endId", endId);
        List<String> industryNames = query.list();
        closeSession();
        return industryNames;
    }
}

