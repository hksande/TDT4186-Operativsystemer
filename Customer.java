package sushiBar;

import java.util.Random;

/**
 * This class implements a customer, which is used for holding data
 * and update the statistics
 */
public class Customer {

    /**
     *  Creates a new Customer.  Each customer should be given a
     *  unique ID
     */

    public static int idCounter = 1;
    private int id;


    public Customer() {
        // TODO: Implement required functionality.
        this.id = Customer.idCounter;
        Customer.idCounter++;
    }


    /**
     * Here you should implement the functionality for ordering food
     * as described in the assignment.
     */
    public void order() throws InterruptedException {
        // TODO: Implement required functionality.
        Random random = new Random();

        // amountOrders makes more sense to me than numbered orders

        int amountOrders = random.nextInt(SushiBar.maxOrder) + 1; // From 1 to maxOrder.
        int amountEatingOrders = random.nextInt(amountOrders); // From 1 to amountOrders.
        int amountTakeawayOrders = amountOrders - amountEatingOrders;

        SushiBar.servedOrders.add(amountEatingOrders);
        SushiBar.takeawayOrders.add(amountTakeawayOrders);
        SushiBar.totalOrders.add(amountOrders);

        SushiBar.write(Thread.currentThread().getName() + ": Customer " + this.id + " is eating.");

        Thread.sleep(SushiBar.customerWait * amountEatingOrders); // The time the customers take to eat
    }

    /**
     *
     * @return Should return the customerID
     */
    public int getCustomerID() {
        // TODO: Implement required functionality.
        return this.id;
    }

    // Add more methods as you see fit.
}
