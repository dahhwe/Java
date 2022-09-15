/**
 * Класс содержащий информацию о мебельных объектах.
 */
public class Furniture{
    private String name;
    private String color;
    private Integer length;
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
     * @param newName Имя мебели.
     * @param newColor Цвет мебели.
     * @param newLength Длина мебели.
     * @param newPrice Цена мебели.
     */
    Furniture(String newName, String newColor, Integer newLength, Double newPrice) {
        name = newName;
        color = newColor;
        length = newLength;
        price = newPrice;
    }

    /**
     * Функция возвращает длину объекта.
     * @return Длина объекта.
     */
    public Integer getLength() {
        return length;
    }


    /**
     * Функция устанавливает длину объекта.
     * @param length Длина объекта.
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * Функция возвращает цену объекта.
     * @return Цена объекта.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Функция устанавливает цену объекта.
     * @param price Цена объекта.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Функция возвращает имя объекта.
     * @return Имя объекта.
     */
    public String getName() {
        return name;
    }

    /**
     * Функция устанавливает имя объекта.
     * @param name Имя объекта.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Функция возвращает цвет объекта.
     * @return цвет объекта.
     */
    public String getColor() {
        return color;
    }

    /**
     * Функция устанавливает цвет объекта.
     * @param color Цвет объекта.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Функция возвращает предпочтенный метод доставки.
     * @return Предпочтенный метод доставки
     */
    public String getShippingInfo()
    {
        if (this.length > 50) {
            return "Премиум доставка";
        }
        else if (this.length > 20) {
            return "Средняя доставка";
        }
        else if (this.length > 5) {
            return "Маленькая доставка";
        }
        else {
            return "Параметры не заданы";
        }
    }
}
