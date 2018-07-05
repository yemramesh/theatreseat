package com.theater.seating;

import com.theater.model.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.TreeMap;


public class PrintTheaterSeatingTest {

    Map<Integer, Customer> customerMap = new TreeMap<Integer, Customer>();
    PrintTheaterSeating testingObject;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void init() {
        testingObject = new PrintTheaterSeating();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void printSeatingAssignments_HappyPath() {

        Customer customer = new Customer("Test23", 3);
        Customer customer1 = new Customer("Test44", 4);
        customer.setRowNumber(1);
        customer.setSectionNumber(3);
        customer1.setRowNumber(2);
        customer1.setSectionNumber(1);
        customerMap.put(1, customer);
        customerMap.put(2, customer1);
        testingObject.printSeatingAssignments(customerMap);
        Assert.assertEquals("Test23 Row 1 Section 3\n" +
                "Test44 Row 2 Section 1\n", outContent.toString());


    }

    @Test
    public void printSeatingAssignments_WitComments() {

        Customer customer = new Customer("Test23", 3);
        Customer customer1 = new Customer("Test44", 4);
        customer.setRowNumber(1);
        customer.setSectionNumber(3);
        customer1.setComments(" This is a test");
        customerMap.put(1, customer);
        customerMap.put(2, customer1);
        testingObject.printSeatingAssignments(customerMap);
        Assert.assertEquals("Test23 Row 1 Section 3\n" +
                "Test44 This is a test\n", outContent.toString());


    }

    @Test
    public void printSeatingAssignments_NoSeatingAvailable() {

        Customer customer = new Customer("Test23", 3);
        Customer customer1 = new Customer("Test44", 4);
        customer.setRowNumber(1);
        customer.setSectionNumber(3);
        customerMap.put(1, customer);
        customerMap.put(2, customer1);
        testingObject.printSeatingAssignments(customerMap);
        Assert.assertEquals("Test23 Row 1 Section 3\n" +
                "Test44\n", outContent.toString());


    }
}
