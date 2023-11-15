package pkg_camp;

import java.util.List;

public class ModifyCamp extends CampController {
    public ModifyCamp(Staff staff, List<Camp> createdCampsList) {
        CampController.modifyCamp(staff, createdCampsList);
    }
}
