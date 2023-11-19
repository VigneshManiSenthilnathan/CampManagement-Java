package pkg_camp;

import java.util.ArrayList;
import java.util.List;

public class CampsList {
    private static List<Camp> createdCampsList = new ArrayList<>();

    public static List<Camp> getCreatedCampsList() {
        return createdCampsList;
    }

    // Use when staff creates a new camp in running memory
    public static void appendToList(Camp camp) {
        createdCampsList.add(camp);
    }
    // Use when downloading from database

    public static void setCreatedCampsList(List<Camp> newCreatedCampsList) {
        List<Camp> createdCampsList = CampsList.getCreatedCampsList();
        createdCampsList = newCreatedCampsList;
    }

}