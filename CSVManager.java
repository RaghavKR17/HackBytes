import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVManager {
    private static final String USER_FILE_PATH = "users.csv";
    private static final String OPPORTUNITY_FILE_PATH = "opportunities.csv";

    public void writeOpportunityToCSV(VolunteerOpportunity opportunity) {
        try (FileWriter writer = new FileWriter(OPPORTUNITY_FILE_PATH, true)) {
            String[] data = {opportunity.getTitle(), opportunity.getDescription(), opportunity.getLocation(), opportunity.getPhoneNumber()};
            writer.write(String.join(",", data) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeUserProfileToCSV(String username, UserProfile profile) {
        try (FileWriter writer = new FileWriter(USER_FILE_PATH, true)) {
            StringBuilder userData = new StringBuilder(username);
            for (Map.Entry<String, Integer> entry : profile.getHoursLogged().entrySet()) {
                userData.append(",").append(entry.getKey()).append(":").append(entry.getValue());
            }
            writer.write(userData.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean isUserExists(String username) {
        List<String[]> userData = readCSV(USER_FILE_PATH);
        for (String[] user : userData) {
            if (user[0].equals(username)) {
                return true;
            }
        }
        return false;
    }
    public void writeUserToCSV(User user) {
        try (FileWriter writer = new FileWriter(USER_FILE_PATH, true)) {
            String[] data = {user.getUsername(), user.getPassword()};
            writer.write(String.join(",", data) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<VolunteerOpportunity> searchOpportunitiesInCity(String city) {
        List<VolunteerOpportunity> opportunities = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(OPPORTUNITY_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                String opportunityCity = values[2].toLowerCase(); // City is at index 2
                if (opportunityCity.equals(city)) {
                    VolunteerOpportunity opportunity = new VolunteerOpportunity(values[0], values[1], values[2], values[3]);
                    opportunities.add(opportunity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return opportunities;
    }
    
    public List<VolunteerOpportunity> searchOpportunitiesInCity(String city, String keywords) {
        List<VolunteerOpportunity> opportunities = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(OPPORTUNITY_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                String opportunityCity = values[2].toLowerCase(); // City is at index 2
                if (opportunityCity.equals(city) && containsKeywords(values, keywords)) {
                    VolunteerOpportunity opportunity = new VolunteerOpportunity(values[0], values[1], values[2], values[3]);
                    opportunities.add(opportunity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return opportunities;
    }

    public VolunteerOpportunity findOpportunityByTitle(String title) {
        try (BufferedReader reader = new BufferedReader(new FileReader(OPPORTUNITY_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(title)) {
                    return new VolunteerOpportunity(values[0], values[1], values[2], values[3]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean containsKeywords(String[] values, String keywords) {
        String[] keywordArray = keywords.toLowerCase().split("\\s+");
        for (String keyword : keywordArray) {
            boolean found = false;
            for (String value : values) {
                if (value.toLowerCase().contains(keyword)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }
    public List<String[]> readCSV(String filePath) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
