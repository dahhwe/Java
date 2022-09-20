import java.util.Objects;

/**
 * Производный класс содержащий информацию о дизельном двигателе.
 */
public class dieselEngine extends Engine {

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
    dieselEngine() {
        engineManufacturer = "notSet";
        engineDisplacement = 0.0;
    }

    /**
     * Конструктор с параметрами.
     * @param engineName Название двигателя.
     * @param power Мощность двигателя.
     * @param engineManufacturer Производитель двигателя.
     * @param engineDisplacement Объем двигателя.
     */
    public dieselEngine(String engineName, Double power, String engineManufacturer, Double engineDisplacement) {

        super(engineName, power);
        this.engineManufacturer = engineManufacturer;
        this.engineDisplacement = engineDisplacement;
    }

    /**
     * Сравнение объектов
     * @param o объект для сравнения.
     * @return результат сравнения объектов.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        dieselEngine that = (dieselEngine) o;
        return Objects.equals(engineManufacturer, that.engineManufacturer) &&
                Objects.equals(engineDisplacement, that.engineDisplacement);
    }

    /**
     * Возвращает числовое значение фиксированной длины для любого объекта.
     * @return Числовое значение фиксированной длины для любого объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), engineManufacturer, engineDisplacement);
    }

    /**
     * Вывод информации об объекте.
     * @return Информация объекта.
     */
    @Override
    public String toString() {

        return " | Название — " + getEngineName() + " | Мощность — " + getPower() + " л.с." +
                " | Компания производитель — " + engineManufacturer + " | Объем двигателя в литрах — " +
                engineDisplacement;
    }
}
