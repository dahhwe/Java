import java.util.Objects;

/**
 * Базовый класс содержащий информацию об объектах.
 */
public class Engine {

    /**
     * Название двигателя.
     */
    private String engineName;
    /**
     * Мощность двигателя.
     */
    private Double power;

    /**
     * Конструктор по-умолчанию.
     */
    public Engine() {
        engineName = "notSet";
        power = 0.0;
    }

    /**
     * Конструктор с параметрами.
     *
     * @param engineName Название двигателя.
     * @param power      Мощность двигателя.
     */
    public Engine(String engineName, Double power) {
        this.engineName = engineName;
        this.power = power;
    }

    /**
     * Сравнение объектов
     *
     * @param o объект для сравнения.
     * @return результат сравнения объектов.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return Objects.equals(engineName, engine.engineName) && Objects.equals(power, engine.power);
    }

    /**
     * Возвращает числовое значение фиксированной длины для любого объекта.
     *
     * @return Числовое значение фиксированной длины для любого объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(engineName, power);
    }

    /**
     * Возвращает название двигателя.
     *
     * @return Название двигателя.
     */
    public String getEngineName() {
        return engineName;
    }

    /**
     * Возвращает мощность двигателя.
     *
     * @return Мощность двигателя.
     */
    public Double getPower() {
        return power;
    }

    /**
     * Возвращает значение переменной
     *
     * @param engineName значение переменной
     */
    public void setEngineName(String engineName) {
        if (!engineName.isEmpty()) {
            this.engineName = engineName;
        }
    }

    /**
     * Возвращает значение переменной
     *
     * @param power значение переменной
     */
    public void setPower(Double power) {
        if (power != 0.0) {
            this.power = power;
        }
    }

    /**
     * Вывод информации об объекте.
     *
     * @return Информация объекта.
     */
    @Override
    public String toString() {
        return " | Название двигателя — " + engineName + " | Мощность двигателя — " + power + " л.с.";
    }
}
