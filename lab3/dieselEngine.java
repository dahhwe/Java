import java.util.Objects;

/**
 * Производный класс содержащий информацию о дизельном двигателе.
 */
public class DieselEngine extends Engine {

    /**
     * Производитель двигателя.
     */
    private String engineManufacturer;
    /**
     * Объем двигателя.
     */
    private Double engineDisplacement;

    /**
     * Конструктор по-умолчанию.
     */
    public DieselEngine() {
        engineManufacturer = "notSet";
        engineDisplacement = 0.0;
    }

    /**
     * Конструктор с параметрами.
     *
     * @param engineName         Название двигателя.
     * @param power              Мощность двигателя.
     * @param engineManufacturer Производитель двигателя.
     * @param engineDisplacement Объем двигателя.
     */
    public DieselEngine(String engineName, Double power, String engineManufacturer, Double engineDisplacement) {

        super(engineName, power);
        this.engineManufacturer = engineManufacturer;
        this.engineDisplacement = engineDisplacement;
    }

    /**
     * Сравнение объектов
     *
     * @param o объект для сравнения.
     * @return результат сравнения объектов.
     */
    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        DieselEngine that = (DieselEngine) o;
        return Objects.equals(engineManufacturer, that.engineManufacturer) &&
                Objects.equals(engineDisplacement, that.engineDisplacement);
    }

    /**
     * Возвращает числовое значение фиксированной длины для любого объекта.
     *
     * @return Числовое значение фиксированной длины для любого объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), engineManufacturer, engineDisplacement);
    }

    /**
     * Возвращает значение переменной
     *
     * @param engineManufacturer значение переменной
     */
    public void setEngineManufacturer(String engineManufacturer) {
        if (!engineManufacturer.isEmpty()) {
            this.engineManufacturer = engineManufacturer;
        }
    }

    /**
     * Возвращает значение переменной
     *
     * @param engineDisplacement значение переменной
     */
    public void setEngineDisplacement(Double engineDisplacement) {
        if (engineDisplacement != 0.0) {
            this.engineDisplacement = engineDisplacement;
        }
    }

    /**
     * Вывод информации об объекте.
     *
     * @return Информация объекта.
     */
    @Override
    public String toString() {

        return " | Название — " + getEngineName() + " | Мощность — " + getPower() + " л.с." +
                " | Компания производитель — " + engineManufacturer + " | Объем двигателя — " +
                engineDisplacement + " литров";
    }
}
