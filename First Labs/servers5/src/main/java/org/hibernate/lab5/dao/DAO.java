package org.hibernate.lab5.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DAO {

    /**
     * Статический объект SessionFactory для создания сессий Hibernate.
     * Используется для обеспечения одного экземпляра на всё приложение.
     */
    private static final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    /**
     * Потокобезопасный объект ThreadLocal для сессий Hibernate.
     * Обеспечивает уникальность сессии для каждого потока.
     */
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<>();

    /**
     * Получает текущую сессию или создаёт новую, если таковая не существует.
     *
     * @return текущая сессия Hibernate
     */
    public static Session getSession() {
        Session session = threadLocal.get();
        if (session == null || !session.isOpen()) {
            session = sessionFactory.openSession();
            threadLocal.set(session);
        }
        return session;
    }

    /**
     * Закрывает текущую сессию и удаляет её из ThreadLocal.
     */
    public static void closeSession() {
        Session session = threadLocal.get();
        threadLocal.set(null);
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    /**
     * Начинает новую транзакцию в текущей сессии.
     */
    public static void begin() {
        getSession().beginTransaction();
    }

    /**
     * Подтверждает текущую транзакцию и сохраняет изменения в базе данных.
     */
    public static void commit() {
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        if (transaction != null && transaction.isActive()) {
            transaction.commit();
        }
    }

    /**
     * Отменяет текущую транзакцию и откатывает все изменения, сделанные в этой транзакции.
     */
    public static void rollback() {
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
    }
}
