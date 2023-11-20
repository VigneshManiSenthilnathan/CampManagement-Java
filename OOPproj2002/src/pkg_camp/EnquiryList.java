package pkg_camp;

import java.util.ArrayList;
import java.util.List;

public class EnquiryList {

    public static List<Enquiry> getEnquiriesList() {
        return enquiries;
    }

    // Use when staff creates a new camp in running memory
    public static void appendToList(Enquiry enquiry) {
        enquiries.add(enquiry);
    }
    // Use when downloading from database

    public static void setEnquiriesList(List<Enquiry> newEnquiriesList) {
        List<Enquiry> originalEnquiriesList = EnquiryList.getEnquiriesList();
        originalEnquiriesList = newEnquiriesList;
    }
}