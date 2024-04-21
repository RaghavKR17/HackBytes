import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVManager {
    private static final String OPPORTUNITY_FILE_PATH = "opportunities.csv";

    public void writeOpportunityToCSV(VolunteerOpportunity opportunity) {
        try (FileWriter writer = new FileWriter(OPPORTUNITY_FILE_PATH, true)) {
            String[] data = {opportunity.getTitle(), opportunity.getDescription(), opportunity.getLocation(), opportunity.getPhoneNumber()};
            writer.write(String.join(",", data) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
