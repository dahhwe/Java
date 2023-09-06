import java.util.Objects;

/**
 * Производный класс содержащий информацию о дизельном двигателе.
 */
public class Tablet extends ElectronicVirtualMachine {

    /**
     * Модель планшета.
     */
    private String tabletModel;
    /**
     * Емкость аккумулятора.
     */
    private Double batteryCapacity;

    /**
     * Конструктор по-умолчанию.
     */
    public Tablet() {
        super("notSet", 0.0, 0.0);
        this.tabletModel = "notSet";
        this.batteryCapacity = 0.0;
    }

    /**
     * Конструктор с параметрами.
     *
     * @param tabletName      Название планшета.
     * @param tabletPower     Мощность планшета.
     * @param tabletModel     Модель планшета.
     * @param batteryCapacity Емкость аккумулятора.
     */
    public Tablet(String tabletName, Double tabletPower, String tabletModel, Double batteryCapacity) {
        super(tabletName, tabletPower, 0.0);

        if (tabletModel.isEmpty() || batteryCapacity == 0.0) {
            throw new IllegalArgumentException("Название планшета не может быть пустым и " +
                    "емкость аккумулятора не может равняться нулю");
        }

        this.tabletModel = tabletModel;
        this.batteryCapacity = batteryCapacity;
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
        Tablet that = (Tablet) o;
        return Objects.equals(tabletModel, that.tabletModel) &&
                Objects.equals(batteryCapacity, that.batteryCapacity);
    }

    /**
     * Возвращает числовое значение фиксированной длины для любого объекта.
     *
     * @return Числовое значение фиксированной длины для любого объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tabletModel, batteryCapacity);
    }

    /**
     * Возвращает значение переменной
     *
     * @param tabletModel значение переменной
     */
    public void setTabletModel(String tabletModel) {
        if (!tabletModel.isEmpty()) {
            this.tabletModel = tabletModel;
        }
    }

    /**
     * Возвращает значение переменной
     *
     * @param batteryCapacity значение переменной
     */
    public void setBatteryCapacity(Double batteryCapacity) {
        if (batteryCapacity != 0.0) {
            this.batteryCapacity = batteryCapacity;
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
                " | Модель — " + tabletModel + " | Емкость аккумулятора — " +
                batteryCapacity + " мАч";
    }
}
