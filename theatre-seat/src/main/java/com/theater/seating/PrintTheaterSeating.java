package com.theater.seating;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.theater.model.Customer;


public class PrintTheaterSeating {

    /**
     * Get the customer name and seat allotment information and printing log in console output
     * @param customerMap
     */
    public void printSeatingAssignments(Map<Integer, Customer> customerMap) {
        customerMap.forEach((key, customer) -> {
            StringBuilder printAssignment = new StringBuilder();
            printAssignment.append(customer.getName());
            if (StringUtils.isBlank(customer.getComments())) {
                printAssignment.append(" Row " + (customer.getRowNumber() +1) + " Section " + (customer.getSectionNumber() + 1));

            } else if (StringUtils.isNotBlank(customer.getComments())) {
                printAssignment.append(customer.getComments());
            }
            System.out.println(printAssignment);

        });
    }
}
