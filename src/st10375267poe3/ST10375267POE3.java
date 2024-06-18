package st10375267poe3;
// MADOBA SUNNBOY 
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ST10375267POE3 {

    /**
     *
     */
    public static int totalHours = 0;
    public static ArrayList<Object> tasks;

    public static void main(String[] args) {
        UserManagement.registerUser();
        UserManagement.loginUser();
        displayWelcomeMessage();

        boolean running = true;
        while (running) {
            int choice = showMenu();
            switch (choice) {
                case 1 -> TaskManagement.addTask();
                case 2 -> TaskManagement.showReport();
                case 3 -> running = false;
                default -> JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
            }
        }

        JOptionPane.showMessageDialog(null, "Total hours across all tasks: " + totalHours);
    }

    public static void displayWelcomeMessage() {
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");
    }

    public static int showMenu() {
        String input = JOptionPane.showInputDialog(
                "Please choose an option:\n1) Add tasks\n2) Show report\n3) Quit");
        return Integer.parseInt(input);
    }
}
