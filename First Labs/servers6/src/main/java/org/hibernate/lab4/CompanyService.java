package org.hibernate.lab4;

import java.util.List;

/**
 * Интерфейс определяет контракт сервиса для управления сущностями Corporation.
 */
public interface CompanyService {

    /**
     * Сохраняет корпорацию в базе данных.
     *
     * @param corporation Объект корпорации для сохранения.
     */
    void saveCompany(Corporation corporation);

    /**
     * Обновляет существующую корпорацию в базе данных.
     *
     * @param corporation Объект корпорации для обновления.
     */
    void updateCompany(Corporation corporation);

    /**
     * Находит корпорацию по её идентификатору.
     *
     * @param id Идентификатор корпорации для поиска.
     * @return Найденная корпорация или null, если таковая не найдена.
     */
    Corporation findCompanyById(int id);

    /**
     * Удаляет корпорацию из базы данных.
     *
     * @param corporation Объект корпорации для удаления.
     */
    void deleteCompany(Corporation corporation);

    /**
     * Возвращает список всех корпораций в базе данных.
     *
     * @return Список всех корпораций.
     */
    List<Corporation> findAllCompanies();

    /**
     * Получает название корпорации по её идентификатору.
     *
     * @param id Идентификатор корпорации.
     * @return Название корпорации или null, если корпорация не найдена.
     */
    String getName(int id);

    /**
     * Возвращает список названий отраслей корпораций в заданном диапазоне идентификаторов.
     *
     * @param startId Начальный идентификатор диапазона.
     * @param endId   Конечный идентификатор диапазона.
     * @return Список названий отраслей.
     */
    List<String> getIndustryNamesInRange(int startId, int endId);
}
