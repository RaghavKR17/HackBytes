public class VolunteerOpportunity {
    private String title;
    private String description;
    private String location;
    private String phoneNumber;

    public VolunteerOpportunity(String title, String description, String location, String phoneNumber) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.phoneNumber = phoneNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Description: " + description + ", Location: " + location + ", Phone Number: " + phoneNumber;
    }
}
