import java.util.Scanner;

public class Product {
    private boolean isStocked;
    private String nameOfProduct;
    private double price;
    private double discountPercentages;

    public Product(boolean isStocked, String nameOfProduct, double price, double discountPercentages) {
        this.isStocked = isStocked;
        this.nameOfProduct = nameOfProduct;
        this.price = price;
        this.discountPercentages = discountPercentages;
    }

    public boolean getIsStocked() {
        return isStocked;
    }

    public void setIsStocked(boolean isStocked) {
        this.isStocked = isStocked;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountPercentages() {
        return discountPercentages;
    }

    public void setDiscountPercentages(double discountPercentages) {
        this.discountPercentages = discountPercentages;
    }

    public String toString() {
        return "Product: " + this.nameOfProduct + ", In stock: " + this.isStocked + ", Price: " + this.price +
                ", VIP discount: " + this.discountPercentages;
    }
}
