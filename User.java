public class User {
    private String username;
    private String password;
    private UserProfile profile; // New field for user profile

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.profile = new UserProfile();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    // Getter for user profile
    public UserProfile getProfile() {
        return profile;
    }
}
