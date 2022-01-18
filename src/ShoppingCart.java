import java.util.Scanner;

public class ShoppingCart {
    private Product[] products;

    public ShoppingCart(Product[] products) {
        this.products = products;
    }

    public void getProducts() {
        for (int i = 0; i < products.length; i++) {
            System.out.println(products[i]);
        }
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }
}
