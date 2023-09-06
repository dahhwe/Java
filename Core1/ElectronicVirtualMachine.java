import java.util.Objects;

/**
 * Базовый класс содержащий информацию об объектах.
 */
public class ElectronicVirtualMachine {

    /**
     * Название ЭВМ.
     */
    private String computerName;
    /**
     * Мощность ЭВМ в Терафлопс.
     */
    private Double power;

    /**
     * Размер экрана ЭВМ.
     */
    private Double screenSize;

    /**
     * Конструктор по-умолчанию.
     */
    public ElectronicVirtualMachine() {
        computerName = "notSet";
        power = 0.0;
        screenSize = 0.0;
    }

    /**
     * Конструктор с параметрами.
     *
     * @param computerName Название ЭВМ.
     * @param power        Мощность ЭВМ в Терафлопс.
     * @param screenSize   Размер экрана ЭВМ.
     */
    public ElectronicVirtualMachine(String computerName, Double power, Double screenSize) {
        this.computerName = computerName;
        this.power = power;
        this.screenSize = screenSize;
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
        ElectronicVirtualMachine computer = (ElectronicVirtualMachine) o;
        return Objects.equals(computerName, computer.computerName) && Objects.equals(power, computer.power);
    }

    /**
     * Возвращает числовое значение фиксированной длины для любого объекта.
     *
     * @return Числовое значение фиксированной длины для любого объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(computerName, power);
    }

    /**
     * Возвращает название ЭВМ.
     *
     * @return Название ЭВМ.
     */
    public String getComputerName() {
        return computerName;
    }

    /**
     * Возвращает мощность ЭВМ в Терафлопс.
     *
     * @return Мощность ЭВМ в Терафлопс.
     */
    public Double getPower() {
        return power;
    }

    /**
     * Устанавливает название ЭВМ.
     *
     * @param computerName Новое название ЭВМ.
     * @throws IllegalArgumentException Если название пусто или равно null.
     */
    public void setComputerName(String computerName) {
        if (computerName != null && !computerName.isEmpty()) {
            this.computerName = computerName;
        } else {
            throw new IllegalArgumentException("Название компьютера должно быть непустым и не равным null.");
        }
    }

    /**
     * Устанавливает мощность ЭВМ в Терафлопс.
     *
     * @param power Новая мощность ЭВМ в Терафлопс.
     * @throws IllegalArgumentException Если мощность равна null или нулю.
     */
    public void setPower(Double power) {
        if (power != null && power != 0.0) {
            this.power = power;
        } else {
            throw new IllegalArgumentException("Мощность должна быть ненулевой и не равной null.");
        }
    }

    /**
     * Устанавливает размер экрана ЭВМ в дюймах.
     *
     * @param screenSize Новый размер экрана ЭВМ в дюймах.
     * @throws IllegalArgumentException Если размер экрана равен null или нулю.
     */
    public void setScreenSize(Double screenSize) {
        if (screenSize != null && screenSize != 0.0) {
            this.screenSize = screenSize;
        } else {
            throw new IllegalArgumentException("Размер экрана должен быть ненулевым и не равным null.");
        }
    }

    /**
     * Вывод информации об объекте.
     *
     * @return Информация объекта.
     */
    @Override
    public String toString() {
        return " | Название компьютера — " + computerName + " | Мощность — " + power + " Терафлопс | Размер экрана — " + screenSize + " Дюймов";
    }


}
