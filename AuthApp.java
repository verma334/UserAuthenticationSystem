import java.util.*;

class User {
    private String username;
    private String password; // In real apps, use hashing!

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
}

public class AuthApp {
    private static final Scanner sc = new Scanner(System.in);
    private static final Map<String, User> users = new HashMap<>();
    private static User loggedInUser = null;

    public static void main(String[] args) {
        System.out.println("=== Simple Auth System ===");
        boolean running = true;
        while (running) {
            System.out.println("\n1) Sign Up\n2) Login\n3) Logout\n4) Protected Action\n5) Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1": signup(); break;
                case "2": login(); break;
                case "3": logout(); break;
                case "4": protectedAction(); break;
                case "5": running = false; break;
                default: System.out.println("Invalid choice.");
            }
        }
        System.out.println("Goodbye!");
    }

    private static void signup() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        if (users.containsKey(username)) {
            System.out.println("Username already exists.");
            return;
        }
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        users.put(username, new User(username, password));
        System.out.println("User registered successfully!");
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        User u = users.get(username);
        if (u != null && u.getPassword().equals(password)) {
            loggedInUser = u;
            System.out.println("Login successful! Welcome, " + username);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void logout() {
        if (loggedInUser != null) {
            System.out.println("Logged out: " + loggedInUser.getUsername());
            loggedInUser = null;
        } else {
            System.out.println("No user is logged in.");
        }
    }

    private static void protectedAction() {
        if (loggedInUser != null) {
            System.out.println("Protected action executed by " + loggedInUser.getUsername());
        } else {
            System.out.println("Access denied. Please log in first.");
        }
    }
}
