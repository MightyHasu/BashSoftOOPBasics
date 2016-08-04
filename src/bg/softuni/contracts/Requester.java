package bg.softuni.contracts;

public interface Requester extends DataBase{

    void getStudentMarkInCourse(String courseName, String studentName);
    void getStudentsByCourse(String courseName);
}
