package bg.softuni.contracts;

import bg.softuni.models.SoftUniCourse;

import java.util.Map;

public interface Student {

    void SoftUniStudent(String userName);

    String getUserName();

    Map<String, Course> getEnrolledCourses();

    Map<String, Double> getMarksByCourseName();


    void enrollInCourse(Course course);

    void setMarkOnCourse(String courseName, int[] scores);

    String getMarkForCourse(String courseName);
}
