public class Notification {
    public static void sendMessages(String... messages) {
        for (var msg : messages) {
            System.out.println("Notification: " + msg);
        }
    }
}
