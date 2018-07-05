package com.theater.seating;

import com.theater.model.Customer;
import com.theater.model.SeatLayOutAndCustomerRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public class AssignCustomerSeatingTest {
    public static final String TEST = "Test";
    public static final String TEST1 = "Test1";
    AssignCustomerSeating testObject;
    SeatLayOutAndCustomerRequest seatLayOutAndCustomerRequest;
    Customer customer;
    TreeMap<Integer, Customer> customerMap = new TreeMap<Integer, Customer>();



    @Before
    public void init(){

        testObject = new AssignCustomerSeating();
        seatLayOutAndCustomerRequest = new SeatLayOutAndCustomerRequest();
        seatLayOutAndCustomerRequest.setTotalLines(5);
        seatLayOutAndCustomerRequest.setLastLayoutRowNumber(3);
        int[][] seatingMatrix = new int[6][6];
        seatingMatrix[1][1] = 3;
        seatingMatrix[1][2] = 3;
        seatingMatrix[2][1] = 4;
        seatingMatrix[2][2] = 3;
        seatLayOutAndCustomerRequest.setSeatingMatrix(seatingMatrix);

    }

    @Test
    public void testExtractSeatingAndCustomerOrder_HappyPath(){
        customer = new Customer(TEST, 3);
        Customer customer1 = new Customer(TEST1, 4);
        customerMap.put(1, customer);
        customerMap.put(2, customer1);
        seatLayOutAndCustomerRequest.setCustomerMap(customerMap);

        List<StringBuilder> lineReaderText = new ArrayList<>();
        lineReaderText.add(new StringBuilder("3 3"));
        lineReaderText.add(new StringBuilder("4 3"));
        lineReaderText.add(new StringBuilder(" "));
        lineReaderText.add(new StringBuilder("Test 3"));
        lineReaderText.add(new StringBuilder("Test1 4"));
        seatLayOutAndCustomerRequest.setLineReaderText(lineReaderText);


        testObject.processSeating(seatLayOutAndCustomerRequest, 4, 13);
        seatLayOutAndCustomerRequest.getCustomerMap().forEach((key, customer)-> {
            if(customer.getName().equals(TEST)) {
                Assert.assertTrue(customer.getRowNumber() == 1);
                Assert.assertTrue(customer.getSectionNumber() == 1);
            }
            if(customer.getName().equals(TEST1)) {
                Assert.assertTrue(customer.getRowNumber() == 2);
                Assert.assertTrue(customer.getSectionNumber() == 1);
            }

        });
    }

    @Test
    public void testextractSeatingAndCustomerOrder_LargeOrder(){

        customer = new Customer(TEST, 100);
        Customer customer1 = new Customer(TEST1, 4);
        customerMap.put(1, customer);
        customerMap.put(2, customer1);
        seatLayOutAndCustomerRequest.setCustomerMap(customerMap);

        List<StringBuilder> lineReaderText = new ArrayList<>();
        lineReaderText.add(new StringBuilder("3 3"));
        lineReaderText.add(new StringBuilder("4 3"));
        lineReaderText.add(new StringBuilder(" "));
        lineReaderText.add(new StringBuilder("Test 100"));
        lineReaderText.add(new StringBuilder("Test1 4"));
        seatLayOutAndCustomerRequest.setLineReaderText(lineReaderText);


        testObject.processSeating(seatLayOutAndCustomerRequest, 4, 13);
        seatLayOutAndCustomerRequest.getCustomerMap().forEach((key, customer)-> {
            if(customer.getName().equals(TEST)) {
                Assert.assertTrue(customer.getRowNumber() == 0);
                Assert.assertTrue(customer.getComments().equals(Constant.CANNOT_HANDLE_PARTY_MSG));
            }
            if(customer.getName().equals(TEST1)) {
                Assert.assertTrue(customer.getRowNumber() == 2);
                Assert.assertTrue(customer.getSectionNumber() == 1);
            }

        });
    }

    @Test
    public void testextractSeatingAndCustomerOrder_SplitOrder(){

        customer = new Customer(TEST, 9);
        Customer customer1 = new Customer(TEST1, 4);
        customerMap.put(1, customer);
        customerMap.put(2, customer1);
        seatLayOutAndCustomerRequest.setCustomerMap(customerMap);

        List<StringBuilder> lineReaderText = new ArrayList<>();
        lineReaderText.add(new StringBuilder("3 3"));
        lineReaderText.add(new StringBuilder("4 3"));
        lineReaderText.add(new StringBuilder(" "));
        lineReaderText.add(new StringBuilder("Test 100"));
        lineReaderText.add(new StringBuilder("Test1 4"));
        seatLayOutAndCustomerRequest.setLineReaderText(lineReaderText);


        testObject.processSeating(seatLayOutAndCustomerRequest, 4, 13);
        seatLayOutAndCustomerRequest.getCustomerMap().forEach((key, customer)-> {
            if(customer.getName().equals(TEST)) {
                Assert.assertTrue(customer.getRowNumber() == 0);
                Assert.assertTrue(customer.getComments().equals(Constant.SPLIT_PARTY_MSG));
            }
            if(customer.getName().equals(TEST1)) {
                Assert.assertTrue(customer.getRowNumber() == 2);
                Assert.assertTrue(customer.getSectionNumber() == 1);
            }

        });
    }

    @Test
    public void testextractSeatingAndCustomerOrder_CloseToFront(){

        customer = new Customer(TEST, 2);
        Customer customer1 = new Customer(TEST1, 4);
        customerMap.put(1, customer);
        customerMap.put(2, customer1);
        seatLayOutAndCustomerRequest.setCustomerMap(customerMap);
        int[][] seatingMatrix = new int[6][6];
        seatingMatrix[1][1] = 3;
        seatingMatrix[1][2] = 3;
        seatingMatrix[2][1] = 4;
        seatingMatrix[2][2] = 3;
        seatingMatrix[3][1] = 4;
        seatingMatrix[3][2] = 2;
        seatLayOutAndCustomerRequest.setSeatingMatrix(seatingMatrix);

        List<StringBuilder> lineReaderText = new ArrayList<>();
        lineReaderText.add(new StringBuilder("3 3"));
        lineReaderText.add(new StringBuilder("4 3"));
        lineReaderText.add(new StringBuilder("4 2"));
        lineReaderText.add(new StringBuilder(" "));
        lineReaderText.add(new StringBuilder("Test 2"));
        lineReaderText.add(new StringBuilder("Test1 4"));
        seatLayOutAndCustomerRequest.setLineReaderText(lineReaderText);


        testObject.processSeating(seatLayOutAndCustomerRequest, 4, 13);

        seatLayOutAndCustomerRequest.getCustomerMap().forEach((key, customer)-> {
            if(customer.getName().equals(TEST)) {
                Assert.assertTrue(customer.getRowNumber() == 3);
                Assert.assertTrue(customer.getSectionNumber() == 2);
            }
            if(customer.getName().equals(TEST1)) {
                Assert.assertTrue(customer.getRowNumber() == 2);
                Assert.assertTrue(customer.getSectionNumber() == 1);
            }

        });
    }

}
