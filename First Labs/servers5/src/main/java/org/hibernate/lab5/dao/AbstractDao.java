package org.hibernate.lab5.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.lab5.util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * Абстрактный класс для Data Access Object (DAO), обеспечивающий базовую реализацию CRUD-операций.
 *
 * @param <T>  Тип сущности.
 * @param <ID> Тип идентификатора сущности, который должен быть сериализуемым.
 */
public abstract class AbstractDao<T, ID extends Serializable> {

    private final Class<T> clazz;

    /**
     * Конструктор для AbstractDao.
     *
     * @param clazz Класс сущности, с которой будет работать DAO.
     */
    public AbstractDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * Находит сущность по идентификатору.
     *
     * @param id Идентификатор сущности для поиска.
     * @return Найденная сущность или null, если сущность не найдена.
     */
    public T findById(ID id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(clazz, id);
        }
    }

    /**
     * Возвращает список всех сущностей данного типа.
     *
     * @return Список сущностей.
     */
    public List<T> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(clazz);
            Root<T> root = cq.from(clazz);
            cq.select(root);
            return session.createQuery(cq).getResultList();
        }
    }

    /**
     * Сохраняет сущность в базе данных.
     *
     * @param entity Сущность для сохранения.
     */
    public void save(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Обновляет сущность в базе данных.
     *
     * @param entity Сущность для обновления.
     */
    public void update(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Удаляет сущность из базы данных.
     *
     * @param entity Сущность для удаления.
     */
    public void delete(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
