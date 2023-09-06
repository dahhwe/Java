
/**
 * Класс содержащий информацию о мебельных объектах.
 */
public class Furniture {

    /**
     * Название мебели.
     */
    private String name;
    /**
     * Цвет мебели.
     */
    private String color;
    /**
     * Длина мебели.
     */
    private Integer length;
    /**
     * Цена мебели.
     */
    private Double price;

    /**
     * Конструктор с параметрами.
     * @param name Имя мебели.
     * @param color Цвет мебели.
     * @param length Длина мебели.
     * @param price Цена мебели.
     */
    public Furniture(String name, String color, Integer length, Double price) {
        if (price >= 0) {
            this.price = price;
        }
        else {
            throw new IllegalArgumentException("Цена мебели не может быть отрицательной! ");
        }

        boolean expression = length >= 0 && length < Integer.MAX_VALUE;
        assert expression : "Длина вне диапазона!";
        this.length = length;

        if (!name.isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Название профессии не может быть пустым!");
        }

        if (!color.isEmpty()) {
            this.color = color;
        } else {
            throw new IllegalArgumentException("Уровень образования не может быть пустым!");
        }
    }

    /**
     * Конструктор по-умолчанию.
     */
    public Furniture() {
        name = "NoName";
        color = "NoColor";
        length = 0;
        price = 0.0;
    }

    /**
     * Возвращает длину объекта.
     * @return Длина объекта.
     */
    public Integer getLength() { return length; }

    /**
     * Возвращает цену объекта.
     * @return Цена объекта.
     */
    public Double getPrice() { return price; }

    /**
     * Возвращает имя объекта.
     * @return Имя объекта.
     */
    public String getName() { return name; }

    /**
     * Возвращает цвет объекта.
     * @return цвет объекта.
     */
    public String getColor() { return color; }

    /***
     * Устанавливает длину объекта.
     * @param length  Длина объекта
     * @throws NegNumException Исключение отрицательного числа.
     */
    public void setLength(Integer length) throws NegNumException {
        if (length < 0) {
            throw new NegNumException("Длина не может быть отрицательной!");
        }
        this.length = length;
    }

    /**
     *
     * Устанавливает цену объекта.
     * @param price Цена объекта.
     * @throws RangeException Исключение диапазона числа.
     */
    public void setPrice(Double price) throws RangeException {
        if (price >= 0 && price < Integer.MAX_VALUE) {
            this.price = price;
        }
        else {
            try {
                if (Math.signum(price) == -1)
                    throw new NegNumException("Цена вне диапазона!");
            } catch ( NegNumException e) {
                RangeException ex = new RangeException("Число вне диапазона! " + e.getMessage());

                ex.initCause(e);
                throw ex;
            }
        }
    }

    /**
     * Устанавливает цену объекта.
     * @param name Цена объекта.
     * @throws EmptyStringException Исключение пустой строки.
     */
    public void setName(String name) throws EmptyStringException {
        if (!name.isEmpty())
            this.name = name;
        else
            throw new EmptyStringException("Название мебели не может быть пустым!");
    }

    /**
     *
     * Устанавливает цвет объекта.
     *
     * @param color Цвет объекта.
     * @throws EmptyStringException Исключение пустой строки.
     */
    public void setColor(String color) throws EmptyStringException {
        if (!color.isEmpty())
            this.color = color;
        else
            throw new EmptyStringException("Цвет мебели не может быть пустым!");
    }

    /**
     * Возвращает предпочтенный метод доставки.
     * @return Предпочтенный метод доставки.
     */
    public String getShippingInfo()
    {
        int bigLength = 50;
        int midLength = 20;
        int smallLength = 5;

        if (this.length > bigLength) {
            return "Премиум доставка";
        }
        else if (this.length > midLength) {
            return "Средняя доставка";
        }
        else if (this.length >= smallLength) {
            return "Маленькая доставка";
        }
        else {
            return "Параметры не заданы";
        }
    }

    /**
     * Вывод информации об объекте.
     * @return Информация объекта.
     */
    @Override
    public String toString() {
        return  "| Имя - " + name +
                "| Цвет - " + color +
                "| Длина - " + length +
                "| Цена - " + price +
                " ₽";
    }
}
