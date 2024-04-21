import java.util.List;
import java.util.Scanner;

public class VolunteerOpportunityFinder {
    private String city;
    private CSVManager csvManager;
    private User currentUser;

    public VolunteerOpportunityFinder(String city) {
        this.city = city;
        this.csvManager = new CSVManager();
        this.currentUser = null;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Create an account");
            System.out.println("2. Post an opportunity");
            System.out.println("3. Find an opportunity");
            System.out.println("4. Log hours");
            System.out.println("5. View total logged hours");
            System.out.println("6. Exit");
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
                    viewTotalLoggedHours();
                    break;
                case 6:
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
        
        String phoneNumber = "";
        boolean validPhoneNumber = false;
        while (!validPhoneNumber) {
            System.out.print("Enter your phone number for contact: ");
            phoneNumber = scanner.nextLine().strip();
            // Check if the phone number consists of digits only and has at least 10 characters
            if (phoneNumber.matches("\\d{10,10}")) {
                validPhoneNumber = true;
            } else {
                System.out.println("Please enter a valid phone number with 10 digits.");
            }
        }
    
        VolunteerOpportunity opportunity = new VolunteerOpportunity(title, description, city, phoneNumber);
        csvManager.writeOpportunityToCSV(opportunity);
        System.out.println("Opportunity posted successfully!");
    }

    private void findOpportunity(Scanner scanner) {
        List<VolunteerOpportunity> allOpportunities = csvManager.searchOpportunitiesInCity(city);

        if (allOpportunities.isEmpty()) {
            System.out.println("No volunteer opportunities available.");
        } else {
            System.out.println("Available volunteer opportunities:");
            for (VolunteerOpportunity opportunity : allOpportunities) {
                System.out.println(opportunity);
            }
            
            System.out.println();
            System.out.print("Enter keywords to filter opportunities (leave blank to show all): ");
            String keywords = scanner.nextLine();
    
            List<VolunteerOpportunity> filteredOpportunities = csvManager.searchOpportunitiesInCity(city, keywords);
            if (filteredOpportunities.isEmpty()) {
                System.out.println("No opportunities found in your city for the given keywords.");
            } else {
                System.out.println("Filtered opportunities:");
                for (VolunteerOpportunity opportunity : filteredOpportunities) {
                    System.out.println(opportunity);
                }
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

    private void viewTotalLoggedHours() {
        if (currentUser != null) {
            UserProfile profile = currentUser.getProfile();
            int totalHours = profile.getTotalHoursLogged();
            System.out.println("Total logged hours: " + totalHours);
        } else {
            System.out.println("No user is logged in.");
        }
    }
}
