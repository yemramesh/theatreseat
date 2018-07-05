package com.theater.seating;

import java.util.List;
import java.util.Map;

import com.theater.model.Customer;
import com.theater.model.SeatLayOutAndCustomerRequest;


public class ExtractSeatingAndCustomerOrder {

    private int highestSeatSection;
    private int totalTheaterSeats;

    public void extractSeatingAndCustomerOrder(SeatLayOutAndCustomerRequest seatLayOutAndCustomerRequest) {
        for (int row = 0, col = 0, partyKey = 0; row < seatLayOutAndCustomerRequest.getTotalLines(); row++, col++) {
            if (row > seatLayOutAndCustomerRequest.getLastLayoutRowNumber()) {
                partyKey = extractCustomerOrder(seatLayOutAndCustomerRequest.getCustomerMap(), seatLayOutAndCustomerRequest.getLineReaderText(), row, partyKey);

            } else if (row < seatLayOutAndCustomerRequest.getLastLayoutRowNumber()) {
                extractSeatingMatrix(seatLayOutAndCustomerRequest.getLineReaderText(), seatLayOutAndCustomerRequest.getSeatingMatrix(), row, col);
            }

        }

    }

    private void extractSeatingMatrix(List<StringBuilder> lineReaderText, int[][] seatingMatrix, int i, int j) {
        if (!lineReaderText.isEmpty()) {
            String[] sectionList = lineReaderText.get(i).toString().split(" ");
            seatingMatrix[j] = new int[sectionList.length];
            for (int section = 0, k = 0; section < sectionList.length; section++, k++) {
                int sectionSeatsCount = Integer.parseInt(sectionList[section]);
                highestSeatSection = Math.max(highestSeatSection, sectionSeatsCount);
                totalTheaterSeats = totalTheaterSeats + sectionSeatsCount;
                seatingMatrix[j][k] = sectionSeatsCount;
            }
        }
    }

    private int extractCustomerOrder(Map<Integer, Customer> customerMap, List<StringBuilder> lineReaderText, int lineIndex, int partyKey) {
        if (!lineReaderText.isEmpty()) {
            String[] custmomerList = lineReaderText.get(lineIndex).toString().split(" ");
            Customer seatingSection = new Customer(custmomerList[0], Integer.parseInt(custmomerList[1]));
            customerMap.put(partyKey++, seatingSection);
        }
        return partyKey;
    }

    public int getHighestSeatSection() {
        return highestSeatSection;
    }

    public int getTotalTheaterSeats() {
        return totalTheaterSeats;
    }
}
