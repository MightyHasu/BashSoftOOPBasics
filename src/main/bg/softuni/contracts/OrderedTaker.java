package main.bg.softuni.contracts;

public interface OrderedTaker extends DataBase {

    void orderAndTake(String courseName, String orderType, int studentsToTake);
    void orderAndTake(String courseName, String orderType);

}
