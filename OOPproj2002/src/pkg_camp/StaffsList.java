package pkg_camp;

import java.util.ArrayList;
import java.util.List;

public class StaffsList {

    private static List<Staff> staffList = new ArrayList<>();

    public static List<Staff> getStaffList() {
        return staffList;
    }

    // Use when staff creates a new camp in running memory

    public static void appendToStaffList(Staff staff) {
        staffList.add(staff);
    }

    // Use when downloading from database

    public static void setStaffsList(List<Staff> newStaffList) {
        List<Staff> originalStaffList = StaffsList.getStaffList();
        originalStaffList = newStaffList;
    }

}
