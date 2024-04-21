import java.util.List;
import java.util.Scanner;

public class VolunteerOpportunityFinder {
    private String city;
    private CSVManager csvManager;

    public VolunteerOpportunityFinder(String city) {
        this.city = city;
        this.csvManager = new CSVManager();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Create an account");
            System.out.println("2. Post an opportunity");
            System.out.println("3. Find an opportunity");
            System.out.println("4. Log hours");
            System.out.println("5. Exit");
            System.out.print("Please enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    postOpportunity(scanner);
                    break;
                case 3:
                    findOpportunity(scanner);
                    break;
                case 4:
                    logHours(scanner);
                    break;
                case 5:
                    System.out.println("Thank you for using Volunteer Opportunity Finder!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void createAccount(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
    
        // Check if the username already exists
        if (csvManager.isUserExists(username)) {
            System.out.println("Username already exists. Please choose a different username.");
            return;
        }
    
        // Create a new user and save it to the CSV file
        User user = new User(username, password);
        csvManager.writeUserToCSV(user);
    
        System.out.println("Account created successfully!");
    }

    private void postOpportunity(Scanner scanner) {
        System.out.print("Enter title of the opportunity: ");
        String title = scanner.nextLine();
        System.out.print("Enter description of the opportunity: ");
        String description = scanner.nextLine();
        System.out.print("Enter your phone number for contact: ");
        String phoneNumber = scanner.nextLine();

        VolunteerOpportunity opportunity = new VolunteerOpportunity(title, description, city, phoneNumber);
        csvManager.writeOpportunityToCSV(opportunity);
        System.out.println("Opportunity posted successfully!");
    }

    private void findOpportunity(Scanner scanner) {
        System.out.print("Enter keywords to search opportunities: ");
        String keywords = scanner.nextLine();

        List<VolunteerOpportunity> opportunities = csvManager.searchOpportunitiesInCity(city, keywords);
        if (opportunities.isEmpty()) {
            System.out.println("No opportunities found in your city for the given keywords.");
        } else {
            System.out.println("Opportunities found:");
            for (VolunteerOpportunity opportunity : opportunities) {
                System.out.println(opportunity);
            }
        }
    }

    private void logHours(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = authenticateUser(username, password);
        if (user != null) {
            System.out.print("Enter the title of the opportunity you volunteered for: ");
            String opportunityTitle = scanner.nextLine();
            VolunteerOpportunity opportunity = csvManager.findOpportunityByTitle(opportunityTitle);
            if (opportunity != null) {
                System.out.print("Enter the number of hours you volunteered: ");
                int hours = scanner.nextInt();
                user.getProfile().logHours(opportunity.getTitle(), hours);
                System.out.println("Hours logged successfully!");
            } else {
                System.out.println("Opportunity not found.");
            }
        } else {
            System.out.println("Authentication failed. Username or password is incorrect.");
        }
    }

    private User authenticateUser(String username, String password) {
        // Retrieve user data from CSV file
        List<String[]> userData = csvManager.readCSV("users.csv");
    
        // Iterate through user data to find matching username and password
        for (String[] user : userData) {
            if (user[0].equals(username) && user[1].equals(password)) {
                // If username and password match, return the authenticated user
                return new User(username, password);
            }
        }
    
        // If no matching user is found, return null
        return null;
    }
}
