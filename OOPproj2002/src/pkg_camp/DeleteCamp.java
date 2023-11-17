package pkg_camp;

import java.time.LocalDate;
import java.util.List;

public class DeleteCamp {

    public static List<Camp> deleteCamp(Staff staff, List<Camp> createdCampsList) {
        List<Camp> newCampList;
        newCampList = CampController.deleteCamp(staff, createdCampsList);
        return newCampList;
    }
}