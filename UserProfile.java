public class UserProfile {
    private int hoursLogged; // Total hours logged for the user

    public UserProfile() {
        this.hoursLogged = 0;
    }

    // Method to log hours for a volunteer opportunity
    public void logHours(int hours) {
        hoursLogged += hours;
    }

    // Method to get total hours logged for the user
    public int getTotalHoursLogged() {
        return hoursLogged;
    }
}
