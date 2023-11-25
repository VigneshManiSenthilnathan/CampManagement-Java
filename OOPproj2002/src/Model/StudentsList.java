package model;

import java.util.ArrayList;
import java.util.List;

public class StudentsList {

    private static List<Student> studentList = new ArrayList<>();

    public static List<Student> getStudentList() {
        return studentList;
    }

    public static List<String> getStudentUserIDList() {
        List<String> studentUserIDList = new ArrayList<>();
        for (Student student : studentList) {
            studentUserIDList.add(student.getUserID());
        }
        return studentUserIDList;
    }

    // Use when staff creates a new camp in running memory

    public static void appendToStudentList(Student student) {
        studentList.add(student);
    }

    // Use when downloading from database

    public static void setStudentsList(List<Student> newStudentList) {
        List<Student> originalStudentList = StudentsList.getStudentList();
        originalStudentList = newStudentList;
    }

}
