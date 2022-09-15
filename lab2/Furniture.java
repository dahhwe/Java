
public class Furniture implements Comparable<Furniture>{
    private Integer length;

    private Double price;

    private String name;
    private String color;

    Furniture() {
        name = "NoName";
        color = "NoColor";
    }

    Furniture(String newName, String newColor, Integer newLength, Double newPrice) {
        length = newLength;
        price = newPrice;
        name = newName;
        color = newColor;
    }

    @Override
    public int compareTo(Furniture o) {
        return this.length - o.length;
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

    public void setColor(String color) {
        this.color = color;
    }

    public String getShippingInfo()
    {
        if (this.length > 50) {
            return "Premium shipping";
        }
        else if (this.length > 20) {
            return "Medium shipping";
        }
        else if (this.length > 5) {
            return "Small shipping";
        }
        else {
            return "Length is not set";
        }
    }
}
