public class Singleton {
    private static Singleton instance;

    private Singleton() {
        // Prevents external instantiation
    }


    // Singleton double check
    public static Singleton getInstance() {

        if (instance == null) { // 1st check

            synchronized (Singleton.class) {
                if (instance == null) { // 2nd check
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // Method to demonstrate functionality
    public void showMessage() {
        System.out.println("Yo bro, I'm Singleton!");
    }
}
