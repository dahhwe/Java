import java.util.Objects;

/**
 * Производный класс содержащий информацию о мебельных объектах.
 */
public class Notebook extends ElectronicVirtualMachine {

    /**
     * Область использования.
     */
    private String processorName;
    /**
     * Детонационная стойкость бензина.
     */
    private Integer clockSpeed;


    /**
     * Конструктор по-умолчанию.
     */
    public Notebook() {
        super("notSet", 0.0, 0.0);
        this.processorName = "notSet";
        this.clockSpeed = 0;
    }

    /**
     * Конструктор с параметрами.
     *
     * @param engineName    Название двигателя.
     * @param power         Мощность двигателя.
     * @param processorName Область использования.
     * @param clockSpeed    Детонационная стойкость бензина.
     */
    public Notebook(String engineName, Double power,
                    String processorName, Integer clockSpeed) {

        super(engineName, power, 0.0);
        this.processorName = processorName;
        this.clockSpeed = clockSpeed;
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
        Notebook that = (Notebook) o;
        return Objects.equals(processorName, that.processorName) &&
                Objects.equals(clockSpeed, that.clockSpeed);
    }

    /**
     * Возвращает числовое значение фиксированной длины для любого объекта.
     *
     * @return Числовое значение фиксированной длины для любого объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(processorName, clockSpeed);
    }

    /**
     * Возвращает значение переменной
     *
     * @param processorName значение переменной
     */
    public void setProcessorName(String processorName) {
        if (!processorName.isEmpty()) {
            this.processorName = processorName;
        }
    }

    /**
     * Возвращает значение переменной
     *
     * @param clockSpeed значение переменной
     */
    public void setClockSpeed(Integer clockSpeed) {
        if (clockSpeed != 0) {
            this.clockSpeed = clockSpeed;
        }
    }


    /**
     * Вывод информации об объекте.
     *
     * @return Информация объекта.
     */
    @Override
    public String toString() {
        return " | Название — " + getComputerName() + " | Мощность — " + getPower() + " Терафлопс" +
                " | Процессор — " + processorName + " | Тактовая частота процессора — " + clockSpeed + " ГГц";
    }
}
