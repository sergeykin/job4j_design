package email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    public EmailNotification() {
    }

    public void emailTo(User user) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                String subject = "Notification " + user.getName() + " to email " + user.getEmail()+ ".";
                String body = "Add a new event to "+ user.getName();
                send(subject, body, user.getEmail());
            }
        });
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String subject, String body, String email) {
        System.out.println("send:subject="+ subject + " body="+ body + " email="+ email);
    }

    public static void main(String[] args) {
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.emailTo(new User("user1", "email1"));
        emailNotification.emailTo(new User("user2", "email2"));
        emailNotification.emailTo(new User("user3", "email3"));
        emailNotification.close();
    }

}
