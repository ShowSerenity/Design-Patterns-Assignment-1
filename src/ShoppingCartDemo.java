import java.util.Scanner;

public class ShoppingCartDemo {
    public static void main(String[] args) {

        ShoppingCart cart = ShoppingCart.getInstance(); // Singleton instance

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Let the user add products to the cart
        while (true) {
            System.out.println("Enter product details (name, price, quantity), or 'done' to checkout:");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("done")) {
                double estimatedTotalAmount = cart.calculateTotalPrice();
                System.out.println("Estimated total amount to pay: " + estimatedTotalAmount);

                System.out.println("Products in your cart:");
                for (Product product : cart.getCartItems()) {
                    System.out.println(product.getName() + " - $" + product.getPrice() + " x " + product.getQuantity());
                }

                if (cart.isEmpty()) {
                    System.out.println("No products added to the cart.");
                }

                break;
            }

            // Ensuring that user inputs values correctly
            String[] parts = input.split(",");
            if (parts.length != 3) {
                System.out.println("Invalid input. Please provide name, price, and quantity separated by commas.");
                continue;
            }

            String name = parts[0].trim();
            double price = Double.parseDouble(parts[1].trim());
            int quantity = Integer.parseInt(parts[2].trim());

            Product product = new Product(name, price, quantity);
            cart.addProduct(product);
        }

        // Let the user choose a payment strategy
        System.out.println("Choose a payment strategy:");
        System.out.println("1. Credit Card");
        System.out.println("2. PayPal");

        int choice = scanner.nextInt();
        PaymentStrategy paymentStrategy = null;

        scanner.nextLine();

        if (choice == 1) {
            while (true) {
                System.out.print("Enter credit card number (16 numbers): ");
                String cardNumber = scanner.nextLine().trim();
                if (cardNumber.matches("\\d{16}")) {
                    paymentStrategy = new CreditCardPaymentStrategy(cardNumber);
                    break;
                } else {
                    System.out.println("Invalid credit card number. Please enter 16 numbers.");
                }
            }
        } else if (choice == 2) {
            while (true) {
                System.out.print("Enter PayPal email (must contain '@' and end with '.com'): ");
                String email = scanner.nextLine().trim();
                if (email.contains("@") && email.endsWith(".com")) {
                    paymentStrategy = new PayPalPaymentStrategy(email);
                    break;
                } else {
                    System.out.println("Invalid PayPal email. Please enter a valid email.");
                }
            }
        } else {
            System.out.println("Invalid choice. Using default payment strategy.");
            paymentStrategy = new CreditCardPaymentStrategy("1234-5678-9888-8888");
        }

        // Checkout using the selected payment strategy
        cart.checkout(paymentStrategy);

        // Show receipt
        System.out.println("Receipt:");
        System.out.println("----------------------------");
        System.out.println("Payment Amount: $" + cart.calculateTotalPrice());
        System.out.println("Payment Method: " + paymentStrategy.getClass().getSimpleName());
        System.out.println("----------------------------");

    }
}
