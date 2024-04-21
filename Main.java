import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Volunteer Opportunity Finder!");
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your city name:");
        String city = scanner.nextLine().toLowerCase(); // Convert city name to lowercase
        
        // Example usage of the application
        VolunteerOpportunityFinder app = new VolunteerOpportunityFinder(city);
        app.run();
        
        scanner.close();
    }
}
