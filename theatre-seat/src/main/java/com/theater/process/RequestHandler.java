package com.theater.process;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.theater.TheaterSeating;
import com.theater.model.Customer;
import com.theater.model.SeatLayOutAndCustomerRequest;


public class RequestHandler {
	
	final static Logger logger = Logger.getLogger(RequestHandler.class);
    /**
     * Loads and parses the given input file.
     *
     * @param fileName
     * @return
     */
    public SeatLayOutAndCustomerRequest extractInfoFromFile(String fileName) {
        InputStream resourceAsStream = null;
        TreeMap<Integer, Customer> customerMap = new TreeMap<Integer, Customer>();
        SeatLayOutAndCustomerRequest seatLayOutAndCustomerRequest = null;

        try {
            LineNumberReader in = new LineNumberReader(new InputStreamReader(TheaterSeating.class.getClassLoader().getResourceAsStream(fileName)));
            int lastLayoutRowNumber = 0;
            int totalLines = 0;

            List<StringBuilder> lineReaderText = new ArrayList<>();
            String line = null;


            while ((line = in.readLine()) != null) {
                lineReaderText.add(totalLines, new StringBuilder(line));
                if (line.trim().isEmpty()) {
                    lastLayoutRowNumber = in.getLineNumber() - 1;
                }
                totalLines++;
            }

            seatLayOutAndCustomerRequest = setSeatingAndCustomerRequest(customerMap, lastLayoutRowNumber, totalLines, lineReaderText);


        } catch (IOException ioe) {
            logger.error("Error while reading input file", ioe);
        } finally {
            if (resourceAsStream != null) {
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    logger.error("Error while closing input reader" , e);
                }
            }
        }
        return seatLayOutAndCustomerRequest;
    }

    private SeatLayOutAndCustomerRequest setSeatingAndCustomerRequest(TreeMap<Integer, Customer> customerMap, int lastLayoutRowNumber, Integer totalLines, List<StringBuilder> lineReaderText) {
        SeatLayOutAndCustomerRequest seatLayOutAndCustomerRequest;
        int[][] seatingMatrix = new int[lastLayoutRowNumber][];
        seatLayOutAndCustomerRequest = new SeatLayOutAndCustomerRequest();
        seatLayOutAndCustomerRequest.setLastLayoutRowNumber(lastLayoutRowNumber);
        seatLayOutAndCustomerRequest.setLineReaderText(lineReaderText);
        seatLayOutAndCustomerRequest.setCustomerMap(customerMap);
        seatLayOutAndCustomerRequest.setSeatingMatrix(seatingMatrix);
        seatLayOutAndCustomerRequest.setTotalLines(totalLines);
        return seatLayOutAndCustomerRequest;
    }

}
