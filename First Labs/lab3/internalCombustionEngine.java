import java.util.Objects;

/**
 * Производный класс содержащий информацию о мебельных объектах.
 */
public class InternalCombustionEngine extends Engine {

    /**
     * Область использования.
     */
    private String fieldOfUse;
    /**
     * Детонационная стойкость бензина.
     */
    private Integer fuelType;
    /**
     * Часовой расход топлива.
     */
    private Double fuelConsumptionHourly;

    /**
     * Конструктор по-умолчанию.
     */
    public InternalCombustionEngine() {
        fieldOfUse = "notSet";
        fuelType = 0;
        fuelConsumptionHourly = 0.0;
    }

    /**
     * Конструктор с параметрами.
     *
     * @param engineName            Название двигателя.
     * @param power                 Мощность двигателя.
     * @param fieldOfUse            Область использования.
     * @param fuelType              Детонационная стойкость бензина.
     * @param fuelConsumptionHourly Часовой расход топлива.
     */
    public InternalCombustionEngine(String engineName, Double power,
                                    String fieldOfUse, Integer fuelType, Double fuelConsumptionHourly) {

        super(engineName, power);
        this.fieldOfUse = fieldOfUse;
        this.fuelType = fuelType;
        this.fuelConsumptionHourly = fuelConsumptionHourly;
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
        InternalCombustionEngine that = (InternalCombustionEngine) o;
        return Objects.equals(fieldOfUse, that.fieldOfUse) &&
                Objects.equals(fuelType, that.fuelType) &&
                Objects.equals(fuelConsumptionHourly, that.fuelConsumptionHourly);
    }

    /**
     * Возвращает числовое значение фиксированной длины для любого объекта.
     *
     * @return Числовое значение фиксированной длины для любого объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(fieldOfUse, fuelType, fuelConsumptionHourly);
    }

    /**
     * Возвращает значение переменной
     *
     * @param fieldOfUse значение переменной
     */
    public void setFieldOfUse(String fieldOfUse) {
        if (!fieldOfUse.isEmpty()) {
            this.fieldOfUse = fieldOfUse;
        }
    }

    /**
     * Возвращает значение переменной
     *
     * @param fuelType значение переменной
     */
    public void setFuelType(Integer fuelType) {
        if (fuelType != 0) {
            this.fuelType = fuelType;
        }
    }

    /**
     * Возвращает значение переменной
     *
     * @param fuelConsumptionHourly значение переменной
     */
    public void setFuelConsumptionHourly(Double fuelConsumptionHourly) {
        if (fuelConsumptionHourly != 0.0) {
            this.fuelConsumptionHourly = fuelConsumptionHourly;
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
                " | Область использования — " + fieldOfUse + " | Детонационная стойкость бензина — " + fuelType +
                " | часовой расход топлива — " + fuelConsumptionHourly + " л/ч";
    }
}
