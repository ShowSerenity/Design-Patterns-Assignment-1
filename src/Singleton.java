public class Singleton {

    private Singleton() {
        // Prevents external instantiation
    }


    private static final class InstanceHolder {
        private static final Singleton instance = new Singleton();
    }

    // Singleton double check
    public static Singleton getInstance() {

        // 1st check
        // 2nd check
        return InstanceHolder.instance;
    }

    // Method to demonstrate functionality
    public void showMessage() {
        System.out.println("Yo bro, I'm Singleton!");
    }
}
