import java.util.Scanner; // Import Scanner for input

public class SecureLogin {
    // Hardcoded correct credentials for demonstration
    private static final String CORRECT_USERNAME = "Levis Kiprop";
    private static final String CORRECT_PASSWORD = "Kiprop254.";

    // Method to read password with '*' masking
    public static String readPassword(Scanner scanner) {
        try {
            // Turn off echoing (works in some terminals)
            java.io.Console console = System.console();
            if (console != null) {
                char[] pwd = console.readPassword();
                return new String(pwd);
            } else {
                // Fallback for IDEs: read as plain text, print '*' for each char
                System.out.print("");
                String input = scanner.nextLine();
                for (int i = 0; i < input.length(); i++) {
                    System.out.print("*");
                }
                System.out.println();
                return input;
            }
        } catch (Exception e) {
            return scanner.nextLine();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner for input
        int attempts = 0; // Track login attempts
        boolean loggedIn = false; // Track login status

        while (attempts < 3 && !loggedIn) { // Allow up to 3 tries
            System.out.print("Enter username: "); // Prompt for username
            String username = scanner.nextLine(); // Read username
            // Username entry done

            System.out.print("Enter password: "); // Prompt for password
            String password = readPassword(scanner); // Read password with masking
            // Password entry done

            if (username.equals(CORRECT_USERNAME) && password.equals(CORRECT_PASSWORD)) {
                System.out.println("Login successful!"); // Successful login
                loggedIn = true;
            } else {
                System.out.println("Incorrect username or password. Try again."); // Failed login
                attempts++;
            }
        }
        if (!loggedIn) {
            System.out.println("Maximum login attempts exceeded. Access denied."); // Too many tries
        }
        scanner.close(); // Close scanner
    }
}