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
     * Конструктор по-умолчанию.
     */
    Furniture() {
        name = "NoName";
        color = "NoColor";
        length = 0;
        price = 0.0;
    }

    /**
     * Конструктор с параметрами.
     * @param name Имя мебели.
     * @param color Цвет мебели.
     * @param length Длина мебели.
     * @param price Цена мебели.
     */
    Furniture(String name, String color, Integer length, Double price) {
        this.name = name;
        this.color = color;
        this.length = length;
        this.price = price;
    }

    /**
     * Возвращает длину объекта.
     * @return Длина объекта.
     */
    public Integer getLength() { return length; }

    /**
     * Устанавливает длину объекта.
     * @param length Длина объекта.
     */
    public void setLength(Integer length) { if (length > 0) this.length = length; }

    /**
     * Возвращает цену объекта.
     * @return Цена объекта.
     */
    public Double getPrice() { return price; }

    /**
     * Устанавливает цену объекта.
     * @param price Цена объекта.
     */
    public void setPrice(Double price) { if (price > 0) this.price = price; }

    /**
     * Возвращает имя объекта.
     * @return Имя объекта.
     */
    public String getName() { return name; }

    /**
     * Устанавливает имя объекта.
     * @param name Имя объекта.
     */
    public void setName(String name) { if (!name.isEmpty()) this.name = name; }

    /**
     * Возвращает цвет объекта.
     * @return цвет объекта.
     */
    public String getColor() { return color; }

    /**
     * Устанавливает цвет объекта.
     * @param color Цвет объекта.
     */
    public void setColor(String color) { if (!color.isEmpty()) this.color = color; }

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
        return  " | Имя - '" + name + '\'' +
                "| Цвет - '" + color + '\'' +
                "| Длина - " + length +
                "| Цена - " + price +
                " ₽";
    }
}
