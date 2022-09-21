import java.util.Objects;

/**
 * Производный класс содержащий информацию о реактивном двигателе.
 */
public class jetEngine extends Engine {

    private String jetsUse;
    /**
     * На каких самолетах устанавливается данный двигатель.
     */
    private Double energyEfficiency;
    /**
     * Тяговооружённость.
     */
    private Double thrustToWeightRatio;

    /**
     * Максимальная скорость самолета с данным двигателем.
     */
    private Integer maxSpeed;

    /**
     * Конструктор по-умолчанию.
     */
    public jetEngine() {
        jetsUse = "notSet";
        energyEfficiency = 0.0;
        thrustToWeightRatio = 0.0;
        maxSpeed = 0;
    }

    /**
     * Конструктор с параметрами.
     * @param engineName Название двигателя.
     * @param power Мощность двигателя.
     * @param jetsUse На каких самолетах устанавливается данный двигатель.
     * @param energyEfficiency Энергоэффективность двигателя.
     * @param thrustToWeightRatio Тяговооружённость.
     * @param maxSpeed Максимальная скорость самолета с данным двигателем.
     */
    public jetEngine(String engineName, Double power, String jetsUse, Double energyEfficiency,
                     Double thrustToWeightRatio, Integer maxSpeed) {

        super(engineName, power);
        this.jetsUse = jetsUse;
        this.energyEfficiency = energyEfficiency;
        this.thrustToWeightRatio = thrustToWeightRatio;
        this.maxSpeed = maxSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        jetEngine jetEngine = (jetEngine) o;
        return Objects.equals(jetsUse, jetEngine.jetsUse) &&
                Objects.equals(energyEfficiency, jetEngine.energyEfficiency) &&
                Objects.equals(thrustToWeightRatio, jetEngine.thrustToWeightRatio) &&
                Objects.equals(maxSpeed, jetEngine.maxSpeed);
    }

    /**
     * Возвращает числовое значение фиксированной длины для любого объекта.
     * @return Числовое значение фиксированной длины для любого объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(jetsUse, energyEfficiency, thrustToWeightRatio, maxSpeed);
    }

    /**
     * Вывод информации об объекте.
     * @return Информация объекта.
     */
    @Override
    public String toString() {
        return " | Название — " + getEngineName() + " | Мощность — " + getPower() + " л.с." +
                " | Установлен на — " + jetsUse + " | Энергоэффективность — " + energyEfficiency +
                " | Тяговооружённость — " + thrustToWeightRatio +
                " | Макс. скорость самолета — " + maxSpeed;
    }
}


