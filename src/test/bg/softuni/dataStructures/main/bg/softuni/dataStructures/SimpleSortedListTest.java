package main.bg.softuni.dataStructures;

import main.bg.softuni.contracts.SimpleOrderedBag;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SimpleSortedListTest {

    private SimpleOrderedBag<String> names;

    @Before
    public void seUp() {
        this.names = new SimpleSortedList<>(String.class);
    }

    @Test
    public void testEmptyCtor() {
        this.names = new SimpleSortedList<String>(String.class);
        Assert.assertEquals(16, this.names.capacity());
        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void testCtorWithInnitialCapacity() {
        this.names = new SimpleSortedList<>(String.class, 20);
        Assert.assertEquals(20, this.names.capacity());
        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void testCtorWithInnitialComparer() {
        this.names = new SimpleSortedList<String>(String.class,
                String.CASE_INSENSITIVE_ORDER);
        Assert.assertEquals(16, this.names.capacity());
        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void testCtorWithAllParams() {
        this.names = new SimpleSortedList<String>(
                String.class,
                String.CASE_INSENSITIVE_ORDER,
                30);
        Assert.assertEquals(30, this.names.capacity());
        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void testAddIncreasesSize() {
        this.names.add("Nasko");
        Assert.assertEquals(1, this.names.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullThrowsException() {
        this.names.add(null);
    }

    @Test
    public void testAddUnsortedDataIsHeldSorted() {
        //Arrange
        this.names.add("Rosen");
        this.names.add("Georgi");
        this.names.add("Balkan");
        String expected = "BalkanGeorgiRosen";
        //Act
        StringBuilder actual = new StringBuilder();
        for (String name : names) {
            actual.append(name);
        }
        //Assert
        Assert.assertEquals(expected, actual.toString());
    }

    @Test
    public void testAddingMoreThanInitialCapacity() {
        //Arrange
        for (int i = 0; i < 17; i++) {
            this.names.add("Ivan");
        }
        String expected = "17";
        //Act
        String actual = String.valueOf(this.names.size());
        //Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAddingAllFromCollectionIncreasesSize() {
        //Arrange
        List<String> list = new ArrayList<>();
        list.add("Georgi");
        list.add("Pesho");
        this.names.addAll(list);
        String expected = "2";
        //Act
        String actual = String.valueOf(this.names.size());
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddingAllFromNullThrowsException() {
        //Arrange
        this.names = new SimpleSortedList<String>(String.class);
        this.names.addAll(null);
    }

    @Test
    public void testAddAllKeepsSorted() {
        //Arrange
        List<String> list = new ArrayList<>();
        list.add("Georgi");
        list.add("Yakim");
        list.add("Pesho");
        this.names.addAll(list);
        String expected = "GeorgiPeshoYakim";
        //Act
        StringBuilder actual = new StringBuilder();
        for (String name : names) {
            actual.append(name);
        }
        //Assert
        Assert.assertEquals(expected, actual.toString());
    }

    @Test
    public void testRemoveValidElementDecreasesSize() {
        //Arrange
        this.names = new SimpleSortedList<String>(String.class);
        this.names.add("Ivan");
        this.names.remove("Ivan");
        String expected = "0";
        //Act
        String actual = String.valueOf(this.names.size());
        //Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRemoveValidElementRemovesSelectedOne() {
        //Arrange
        this.names = new SimpleSortedList<String>(String.class);
        this.names.add("Ivan");
        this.names.add("Nasko");
        this.names.remove("Ivan");
        String expected = "Nasko";
        //Act
        StringBuilder actual = new StringBuilder();
        for (String name : names) {
            actual.append(name);
        }
        //Assert
        Assert.assertEquals(expected, actual.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemovingNullThrowsException() {
        //Arrange
        this.names = new SimpleSortedList<String>(String.class);
        this.names.add("Ivan");
        this.names.add("Nasko");
        this.names.remove(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testJoinWithNull() {
        //Arrange
        this.names = new SimpleSortedList<String>(String.class);
        this.names.add("Ivan");
        this.names.add("Nasko");
        this.names.joinWith(null);
    }

    @Test
    public void testJoinWorksFine() {
        //Arrange
        this.names = new SimpleSortedList<String>(String.class);
        this.names.add("Ivan");
        this.names.add("Nasko");
        String expected = "Ivan,Nasko";
        //Act
        String actual = this.names.joinWith(",");
        //Assert
        Assert.assertEquals(expected, actual);
    }
}