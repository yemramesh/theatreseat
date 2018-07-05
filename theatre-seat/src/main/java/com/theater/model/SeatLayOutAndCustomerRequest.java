package com.theater.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * SeatLayOutAndCustomerRequest is the object model of all the information
 * about the seating in the theater and Customer request fed into this Exercise in file format.
 */
public class SeatLayOutAndCustomerRequest {

	Map<Integer, Customer> customerMap = new TreeMap<Integer, Customer>();
    int lastLayoutRowNumber = 0;
    int totalLines = 0;
    int[][] seatingMatrix;
    List<StringBuilder> lineReaderText = new ArrayList<>();

    public Map<Integer, Customer> getCustomerMap() {
        return customerMap;
    }

    public void setCustomerMap(Map<Integer, Customer> customerMap) {
        this.customerMap = customerMap;
    }

    public int getLastLayoutRowNumber() {
        return lastLayoutRowNumber;
    }

    public void setLastLayoutRowNumber(int emptyLineNumber) {
        this.lastLayoutRowNumber = emptyLineNumber;
    }

    public Integer getTotalLines() {
        return totalLines;
    }

    public void setTotalLines(Integer totalLines) {
        this.totalLines = totalLines;
    }

    public int[][] getSeatingMatrix() {
        return seatingMatrix;
    }

    public void setSeatingMatrix(int[][] seatingMatrix) {
        this.seatingMatrix = seatingMatrix;
    }

    public List<StringBuilder> getLineReaderText() {
        return lineReaderText;
    }

    public void setLineReaderText(List<StringBuilder> lineReaderText) {
        this.lineReaderText = lineReaderText;
    }
}
