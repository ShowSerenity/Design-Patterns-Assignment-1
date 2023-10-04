public class Singleton {
    private static Singleton instance;

    private Singleton() {
        // Prevents external instantiation
    }

    public static Singleton getInstance() {

        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    // Method to demonstrate functionality
    public void showMessage() {
        System.out.println("Yo bro, I'm Singleton!");
    }
}
