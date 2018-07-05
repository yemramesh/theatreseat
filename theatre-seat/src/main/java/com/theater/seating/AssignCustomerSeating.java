package com.theater.seating;

import java.util.Map;

import org.apache.log4j.Logger;

import com.theater.model.Customer;
import com.theater.model.SeatLayOutAndCustomerRequest;

public class AssignCustomerSeating implements Constant {

	private int highestSeatSection;
	private int totalTheaterSeats;

	static final Logger logger = Logger.getLogger(AssignCustomerSeating.class);

	public void processSeating(SeatLayOutAndCustomerRequest seatLayOutAndCustomerRequest, int highestSeatSection,
			int totalTheaterSeats) {

		this.highestSeatSection = highestSeatSection;
		this.totalTheaterSeats = totalTheaterSeats;
		Map<Integer, Customer> customerMap = seatLayOutAndCustomerRequest.getCustomerMap();

		customerMap.forEach((key, customer) -> {

			if (isExceedMaxNumberOfSeats(customer, seatLayOutAndCustomerRequest.getSeatingMatrix())) {
				customer.setComments(CANNOT_HANDLE_PARTY_MSG);
			} else if (isAllocationSplitRequired(customer, seatLayOutAndCustomerRequest.getSeatingMatrix())) {
				customer.setComments(SPLIT_PARTY_MSG);
			} else if (allocateSeatsExactInPriority(customer, seatLayOutAndCustomerRequest.getSeatingMatrix())) {
				logger.info("Allocated seat in priority rows with exact...." + customer.getName());
			} else if (allocatSeatsInPriority(customer, seatLayOutAndCustomerRequest.getSeatingMatrix())) {
				logger.info("Allocated seat in priority rows...." + customer.getName());
			} else if (allocateSeats(customer, seatLayOutAndCustomerRequest.getSeatingMatrix())) {
				logger.info("Allocated seat in the layout...." + customer.getName());
			} else {
				logger.info("Unable to allocate seats....." + customer.getName());
			}

		});
	}

	private boolean allocateSeats(Customer customer, int[][] seatingLayout) {
		try {
			for (int row = 0; row < seatingLayout.length; row++) {
				for (int section = 0; section < seatingLayout[row].length; section++) {
					if (seatingLayout[row][section] >= customer.getRequiredSeat()) {
						customer.setRowNumber(row);
						customer.setSectionNumber(section);
						seatingLayout[row][section] = seatingLayout[row][section] - customer.getRequiredSeat();
						logger.info("Seating allocated in front priority for customer: " + customer.getName());
						return true;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error in allocate seats in rows", e);
		}

		return false;
	}

	private boolean allocatSeatsInPriority(Customer customer, int[][] seatingLayout) {
		int priortyRows;
		if (seatingLayout == null) {
			logger.debug("Seating layout is null in allocatSeatsInPriority, unable to process.......");
			return false;
		}
		try {
			if (seatingLayout.length > 2) {
				priortyRows = (int) Math.ceil(seatingLayout.length / 2);
			} else {
				priortyRows = seatingLayout.length;
			}

			for (int row = 0; row < seatingLayout.length; row++) {
				for (int section = 0; section < seatingLayout[row].length; section++) {
					if ((seatingLayout[row][section] > customer.getRequiredSeat() && row < priortyRows)) {
						customer.setRowNumber(row);
						customer.setSectionNumber(section);
						seatingLayout[row][section] = seatingLayout[row][section] - customer.getRequiredSeat();
						logger.info("Seating allocated in front priority for customer: " + customer.getName());
						return true;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error in allocate seats in priorty rows", e);
		}

		return false;
	}

	private boolean allocateSeatsExactInPriority(Customer customer, int[][] seatingLayout) {
		int priortyRows;
		if (seatingLayout == null) {
			logger.debug("Seating layout is null in allocateSeatsExactInPriority, unable to process.......");
			return false;
		}
		try {
			if (seatingLayout.length > 2) {
				priortyRows = (int) Math.ceil(seatingLayout.length / 2);
			} else {
				priortyRows = seatingLayout.length;
			}

			for (int row = 0; row < seatingLayout.length; row++) {
				for (int section = 0; section < seatingLayout[row].length; section++) {
					if ((seatingLayout[row][section] == customer.getRequiredSeat() && row < priortyRows)) {
						customer.setRowNumber(row);
						customer.setSectionNumber(section);
						seatingLayout[row][section] = seatingLayout[row][section] - customer.getRequiredSeat();
						logger.info("Seating allocated in exact match in front priority for customer: "
								+ customer.getName());
						return true;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error in allocate seats in exact and priorty rows", e);
		}

		return false;
	}

	private boolean isExceedMaxNumberOfSeats(Customer customer, int[][] seatingMatrix) {
		boolean result = false;
		if (customer.getRequiredSeat() > totalTheaterSeats) {
			result = true;
		}
		return result;
	}

	private boolean isAllocationSplitRequired(Customer customer, int[][] seatingMatrix) {
		boolean result = false;
		if (customer.getRequiredSeat() > highestSeatSection) {
			result = true;
		}
		return result;
	}

}
