import java.util.Objects;

public class jetEngine extends Engine {

    private String jetsUse;
    private Double energyEfficiency;
    private Double thrustToWeightRatio;

    private Integer maxSpeed;

    public jetEngine() {
        jetsUse = "notSet";
        energyEfficiency = 0.0;
        thrustToWeightRatio = 0.0;
        maxSpeed = 0;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(jetsUse, energyEfficiency, thrustToWeightRatio, maxSpeed);
    }

    @Override
    public String toString() {
        return " | Название — " + getEngineName() + " | Мощность — " + getPower() + " л.с." +
                " | Установлен на — " + jetsUse + " | Энергоэффективность — " + energyEfficiency +
                " | Тяговооружённость — " + thrustToWeightRatio +
                " | Макс. скорость самолета — " + maxSpeed;
    }
}


