package strategies;

import models.Bill;
import models.ParkingLot;
import models.Ticket;

public interface FeesCalculationStrategy {
    /**
     *
     * @param configuration: contains base price and multiplier for different vehicle types
     * @param ticket : helpful to determine duration of stay of vehicle and slot type can
     *                 determine if additional cost incurred for cases like Electric charging
     * @param bill :  amount to be set against BILL.
     * @return
     */
    public double generateBillAmount(ParkingLot configuration, Ticket ticket, Bill bill);
}
