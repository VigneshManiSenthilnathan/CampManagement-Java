package pkg_camp;

import java.util.List;

public class DeleteCamp extends CampController {

    public static List<Camp> deleteCamp(Staff staff, List<Camp> createdCampsList) {
        List<Camp> newCampList;
        newCampList = CampController.deleteCamp(staff, createdCampsList);
        return newCampList;
    }
}