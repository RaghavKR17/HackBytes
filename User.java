public class User {
    private String username;
    private String password;
    private UserProfile profile; // UserProfile object for the user

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.profile = new UserProfile(username); // Initialize UserProfile with the username
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Getter for UserProfile
    public UserProfile getProfile() {
        return profile;
    }
}
