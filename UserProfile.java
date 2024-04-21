import java.util.List;

public class UserProfile {

    private int hoursLogged; // Total hours logged for the user
    public UserProfile(String username) {
        // Initialize hours logged by reading from the CSV file
        this.hoursLogged = readHoursLoggedFromCSV(username);
    }

    // Method to log hours for a volunteer opportunity
    public void logHours(int hours) {
        hoursLogged += hours;
    }

    // Method to get total hours logged for the user
    public int getTotalHoursLogged() {
        return hoursLogged;
    }

    // Method to read hours logged from the CSV file
    private int readHoursLoggedFromCSV(String username) {
        CSVManager csvManager = new CSVManager();
        List<String[]> userData = csvManager.readCSV(CSVManager.USER_FILE_PATH);
        for (String[] user : userData) {
            if (user[0].equals(username)) { // Assuming username is at index 0
                try {
                    return Integer.parseInt(user[2]); // Assuming hours logged is at index 2
                } catch (NumberFormatException e) {
                    // Handle the case where the hours logged cannot be parsed as an integer
                    System.out.println("Error: Hours logged is not a valid integer.");
                    return 0; // Return 0 if there's an error parsing the hours logged
                }
            }
        }
        // Return 0 if no matching user is found
        return 0;
    }
}
