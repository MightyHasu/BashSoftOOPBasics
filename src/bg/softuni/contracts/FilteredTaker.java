package bg.softuni.contracts;


public interface FilteredTaker extends DataBase{

    void filterAndTake(String courseName, String filter);
    void filterAndTake(String courseName, String filter, int studentsToTake);

}
