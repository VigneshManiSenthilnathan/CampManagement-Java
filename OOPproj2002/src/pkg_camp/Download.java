package pkg_camp;

import java.io.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Download {
    // class to pull data from excel file into running program
    // Load into createdCamps the camps from the excel file camps.xlsx

    public static List<CampInfoController> loadCamps(List<CampInfoController> createdCamps) {
        try {

            String filePath = "OOPproj2002/src/pkg_camp/camps.xlsx";

            File file = new File(filePath);
            if (!file.exists()) {
                return createdCamps;
            }

            // The FileInputStream provides the necessary stream to read the bytes from the
            // file which is required for operations like reading an Excel workbook.

            FileInputStream excelFile = new FileInputStream(new File(filePath));

            // Upload --> Upload runtime memory to excel (Already have camps.xlsx, if not
            // create new one)
            // Download --> Download excel to runtime memory (Need to check if the excel
            // exists)

            Workbook workbook = WorkbookFactory.create(excelFile);
            Sheet sheet = workbook.getSheet("Camps");

            // Check if the file is empty
            if (sheet.getLastRowNum() <= 0) { // Assuming the header is in the first row
                excelFile.close();
                workbook.close();
                throw new IOException("The supplied file is empty.");
            } else {
                System.out.println(sheet.getLastRowNum());
            }

            for (Row row : sheet) {
                // ignore the initial header row
                if (row.getRowNum() == 0) {
                    continue;
                }

                else {
                    String campName = row.getCell(0).getStringCellValue();
                    String dates = row.getCell(1).getStringCellValue();
                    LocalDate campDates = LocalDate.parse(dates, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                    String closedate = row.getCell(2).getStringCellValue();
                    LocalDate campRegClosingDate = LocalDate.parse(closedate,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                    String campFaculty = row.getCell(3).getStringCellValue();
                    String campLocation = row.getCell(4).getStringCellValue();
                    int campAttendeeSlots = (int) row.getCell(5).getNumericCellValue();
                    int campCommitteeSlots = (int) row.getCell(6).getNumericCellValue();
                    String campDescription = row.getCell(7).getStringCellValue();
                    boolean campVisibility = row.getCell(8).getBooleanCellValue();
                    String campStaffInCharge = row.getCell(9).getStringCellValue();

                    CampInfoController camp = new CampInfoController(campName, campDates, campRegClosingDate,
                            campFaculty,
                            campLocation, campAttendeeSlots, campCommitteeSlots, campDescription, campStaffInCharge,
                            campVisibility);

                    createdCamps.add(camp);
                    System.out.println("Camp: " + campName + " loaded successfully.");
                }
            }
            excelFile.close();
            workbook.close();
        }

        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading camps from excel file. Exception: " + e.getClass().getSimpleName()
                    + ", Message: " + e.getMessage());
        }
        return createdCamps;
    }
}
