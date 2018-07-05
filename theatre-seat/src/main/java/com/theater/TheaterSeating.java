package com.theater;

import com.theater.model.SeatLayOutAndCustomerRequest;
import com.theater.process.RequestHandler;
import com.theater.seating.AssignCustomerSeating;
import com.theater.seating.ExtractSeatingAndCustomerOrder;
import com.theater.seating.PrintTheaterSeating;

import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * Main class to execute for see the output
 */
public class TheaterSeating {

	static final Logger logger = Logger.getLogger(TheaterSeating.class);

    public static void main(String[] args) throws IOException {

    	logger.info("Application main Program strarting....");
        RequestHandler requestHandler = new RequestHandler();
        SeatLayOutAndCustomerRequest seatLayOutAndCustomerRequest = requestHandler.extractInfoFromFile("theater_layout.txt");

        ExtractSeatingAndCustomerOrder extractSeatingAndCustomerOrder = new ExtractSeatingAndCustomerOrder();
        extractSeatingAndCustomerOrder.extractSeatingAndCustomerOrder(seatLayOutAndCustomerRequest);

        AssignCustomerSeating seating = new AssignCustomerSeating();
        
        seating.processSeating(seatLayOutAndCustomerRequest, extractSeatingAndCustomerOrder.getHighestSeatSection(), extractSeatingAndCustomerOrder.getTotalTheaterSeats());
        
        PrintTheaterSeating printSeating = new PrintTheaterSeating();

        printSeating.printSeatingAssignments(seatLayOutAndCustomerRequest.getCustomerMap());


    }
}
