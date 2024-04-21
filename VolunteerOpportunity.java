public class VolunteerOpportunity {
    private String description;
    private String location;
    private String phoneNumber;

    public VolunteerOpportunity(String description, String location, String phoneNumber) {
        this.description = description;
        this.location = location;
        this.phoneNumber = phoneNumber;
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
        return "Description: " + description + ", Location: " + location + ", Phone Number: " + phoneNumber;
    }
}
