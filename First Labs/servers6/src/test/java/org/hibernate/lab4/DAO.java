package org.hibernate.lab4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DAO {

    // Статический объект SessionFactory для обеспечения одного экземпляра на всё приложение
    private static final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    // Потокобезопасный объект Session для обеспечения одной сессии на поток
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<>();

    public static Session getSession() {
        // Получение текущей сессии для этого потока или создание новой, если она отсутствует
        Session session = threadLocal.get();
        if (session == null || !session.isOpen()) {
            session = sessionFactory.openSession();
            threadLocal.set(session);
        }
        return session;
    }

    public static void closeSession() {
        // Закрытие текущей сессии для этого потока
        Session session = threadLocal.get();
        threadLocal.set(null);
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public static void begin() {
        // Начало новой транзакции
        getSession().beginTransaction();
    }

    public static void commit() {
        // Подтверждение текущей транзакции
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        if (transaction != null && transaction.isActive()) {
            transaction.commit();
        }
    }

    public static void rollback() {
        // Откат текущей транзакции
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
    }
}
