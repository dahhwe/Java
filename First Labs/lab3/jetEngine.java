import java.util.Objects;

/**
 * Производный класс содержащий информацию о реактивном двигателе.
 */
public class JetEngine extends Engine {

    /**
     * На каких самолетах устанавливается данный двигатель.
     */
    private String jetsUse;
    /**
     * Энергоэффективность
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
    public JetEngine() {
        jetsUse = "notSet";
        energyEfficiency = 0.0;
        thrustToWeightRatio = 0.0;
        maxSpeed = 0;
    }

    /**
     * Конструктор с параметрами.
     *
     * @param engineName          Название двигателя.
     * @param power               Мощность двигателя.
     * @param jetsUse             На каких самолетах устанавливается данный двигатель.
     * @param energyEfficiency    Энергоэффективность двигателя.
     * @param thrustToWeightRatio Тяговооружённость.
     * @param maxSpeed            Максимальная скорость самолета с данным двигателем.
     */
    public JetEngine(String engineName, Double power, String jetsUse, Double energyEfficiency,
                     Double thrustToWeightRatio, Integer maxSpeed) {

        super(engineName, power);
        this.jetsUse = jetsUse;
        this.energyEfficiency = energyEfficiency;
        this.thrustToWeightRatio = thrustToWeightRatio;
        this.maxSpeed = maxSpeed;
    }

    @Override
    public boolean equals(Object o) {

        if (!super.equals(o)) return false;
        JetEngine jetEngine = (JetEngine) o;
        return Objects.equals(jetsUse, jetEngine.jetsUse) &&
                Objects.equals(energyEfficiency, jetEngine.energyEfficiency) &&
                Objects.equals(thrustToWeightRatio, jetEngine.thrustToWeightRatio) &&
                Objects.equals(maxSpeed, jetEngine.maxSpeed);
    }

    /**
     * Возвращает числовое значение фиксированной длины для любого объекта.
     *
     * @return Числовое значение фиксированной длины для любого объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(jetsUse, energyEfficiency, thrustToWeightRatio, maxSpeed);
    }

    /**
     * Устанавливает значение переменной.
     *
     * @param jetsUse Значение переменной.
     */
    public void setJetsUse(String jetsUse) {
        if (!jetsUse.isEmpty()) {
            this.jetsUse = jetsUse;
        }
    }

    /**
     * Возвращает значение переменной
     *
     * @param energyEfficiency значение переменной
     */
    public void setEnergyEfficiency(Double energyEfficiency) {
        if (energyEfficiency != 0.0) {
            this.energyEfficiency = energyEfficiency;
        }
    }

    /**
     * Возвращает значение переменной
     *
     * @param thrustToWeightRatio значение переменной
     */
    public void setThrustToWeightRatio(Double thrustToWeightRatio) {
        if (thrustToWeightRatio != 0.0) {
            this.thrustToWeightRatio = thrustToWeightRatio;
        }
    }

    /**
     * Возвращает значение переменной
     *
     * @param maxSpeed значение переменной
     */
    public void setMaxSpeed(Integer maxSpeed) {
        if (maxSpeed != 0) {
            this.maxSpeed = maxSpeed;
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
                " | Установлен на — " + jetsUse + " | Энергоэффективность — " + energyEfficiency +
                " % | Тяговооружённость — " + thrustToWeightRatio +
                " вт/кг | Макс. скорость — " + maxSpeed + "км/ч";
    }
}


