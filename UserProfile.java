import java.util.HashMap;
import java.util.Map;

public class UserProfile {
    private Map<String, Integer> hoursLogged; // Map to store hours logged for each volunteer opportunity

    public UserProfile() {
        this.hoursLogged = new HashMap<>();
    }

    // Method to log hours for a volunteer opportunity
    public void logHours(String opportunityTitle, int hours) {
        hoursLogged.put(opportunityTitle, hoursLogged.getOrDefault(opportunityTitle, 0) + hours);
    }

    // Method to get total hours logged for all volunteer opportunities
    public int getTotalHoursLogged() {
        int totalHours = 0;
        for (int hours : hoursLogged.values()) {
            totalHours += hours;
        }
        return totalHours;
    }
}
