package main.bg.softuni.io.commands;

import main.bg.softuni.annotations.Alias;
import main.bg.softuni.annotations.Inject;
import main.bg.softuni.contracts.Course;
import main.bg.softuni.contracts.Executable;
import main.bg.softuni.contracts.Student;
import main.bg.softuni.dataStructures.SimpleSortedList;
import main.bg.softuni.exceptions.InvalidInputException;
import main.bg.softuni.io.OutputWriter;
import main.bg.softuni.repository.StudentsRepository;

import java.util.Comparator;

@Alias("cdrel")
public class DisplayCommand extends Command implements Executable {

    public DisplayCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 3) {
            throw new InvalidInputException(this.getInput());
        }

        String entityTotDisplay = data[1];
        String sortType = data[2];
        if (entityTotDisplay.equalsIgnoreCase("students")) {
            Comparator<Student> studentComparator =
                    this.createStudentComparator(sortType);
            SimpleSortedList<Student> list =
                    this.repository.getAllStudentsSorted(studentComparator);
            OutputWriter.writeMessageOnNewLine(list.joinWith(System.lineSeparator()));
        } else if (entityTotDisplay.equalsIgnoreCase("courses")) {
            Comparator<Course> studentComparator =
                    this.createCourseComparator(sortType);
            SimpleSortedList<Course> list =
                    this.repository.getAllCourseSorted(studentComparator);
            OutputWriter.writeMessageOnNewLine(list.joinWith(System.lineSeparator()));
        } else {
            throw new InvalidInputException(this.getInput());
        }
    }

    private Comparator<Course> createCourseComparator(String sortType) {
        if (sortType.equalsIgnoreCase("ascending")) {
            return (o1, o2) -> o1.compareTo(o2);
        } else if (sortType.equalsIgnoreCase("descending")) {
            return (o1, o2) -> o2.compareTo(o1);
        } else {
            throw new InvalidInputException(this.getInput());
        }
    }

    private Comparator<Student> createStudentComparator(String sortType) {
        if (sortType.equalsIgnoreCase("ascending")) {
            return (o1, o2) -> o1.compareTo(o2);
        } else if (sortType.equalsIgnoreCase("descending")) {
            return (o1, o2) -> o2.compareTo(o1);
        } else {
            throw new InvalidInputException(this.getInput());
        }
    }

    @Inject
    private StudentsRepository repository;
}
