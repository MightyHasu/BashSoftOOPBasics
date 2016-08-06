package main.bg.softuni.contracts;

import java.io.IOException;

public interface DataBase {

    void unloadData();
    void loadData(String fileName) throws IOException;
}
