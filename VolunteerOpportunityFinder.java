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
            System.out.println("\n1. Post an opportunity");
            System.out.println("2. Find an opportunity");
            System.out.println("3. Exit");
            System.out.print("Please enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    postOpportunity(scanner);
                    break;
                case 2:
                    findOpportunity(scanner);
                    break;
                case 3:
                    System.out.println("Thank you for using Volunteer Opportunity Finder!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
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
}
