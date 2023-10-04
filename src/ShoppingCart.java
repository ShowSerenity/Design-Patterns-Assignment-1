import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> cartItems;

    private static ShoppingCart instance; // Singleton instance

    private ShoppingCart() {
        cartItems = new ArrayList<>();
    }

    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    // Adding products in the cart
    public void addProduct(Product product) {
        cartItems.add(product);
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (Product product : cartItems) {
            total += product.getPrice() * product.getQuantity();
        }
        return total;
    }

    // Get list of products from the cart
    public List<Product> getCartItems() {
        return cartItems;
    }


    // Check whether cart is empty
    public boolean isEmpty() {
        return cartItems.isEmpty();
    }


    // Print out total price based on payment strategy
    public void checkout(PaymentStrategy paymentStrategy) {
        double total = calculateTotalPrice();
        paymentStrategy.processPayment(total);
    }
}
