
public class Furniture {

    private Integer length;
    public Double price;

    public String name;
    public String color;

    Furniture() {
        name = "NoName";
        color = "NoColor";
    }
    Furniture(Integer newLength, Double newPrice, String newName, String newColor) {
            length = newLength;
            price = newPrice;
            name = newName;
            color = newColor;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }


    private static String getShippingInfo() {
        return "shipping";
    }
}