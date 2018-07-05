package com.theater.process;

import com.theater.model.SeatLayOutAndCustomerRequest;
import com.theater.process.RequestHandler;
import org.junit.Assert;
import org.junit.Test;

public class ProcessPreSaleRequestsTest {

    RequestHandler requestHandler;

    @Test
    public void processInputFile_HappyPath() {
        requestHandler = new RequestHandler();

        SeatLayOutAndCustomerRequest seatLayOutAndCustomerRequest = requestHandler.extractInfoFromFile("test_resource.txt");
        Assert.assertNotNull(seatLayOutAndCustomerRequest);
        Assert.assertNotNull(seatLayOutAndCustomerRequest.getCustomerMap());

        seatLayOutAndCustomerRequest.getCustomerMap().forEach((key, customer)->{
            if(customer.getName().equals("Test33")) {
                Assert.assertTrue(customer.getRowNumber() == 1);
                Assert.assertTrue(customer.getSectionNumber() == 1);
            }
            if(customer.getName().equals("Test56")) {
                Assert.assertTrue(customer.getRowNumber() == 2);
                Assert.assertTrue(customer.getSectionNumber() == 2);
            }
        });


    }


    @Test
    public void processInputFile_FileNoIssue() {
        requestHandler = new RequestHandler();
        try {

            requestHandler.extractInfoFromFile("test_resource.txt");
        } catch (Exception e) {
            Assert.fail("Exception " + e);
        }

    }

    @Test
    public void processInputFile_FileNotFound() {
        requestHandler = new RequestHandler();
        try {

            requestHandler.extractInfoFromFile("nonametest_resource.txt");
            Assert.fail("Exception should have been thrown");

        } catch (Exception e) {

        }

    }
}
