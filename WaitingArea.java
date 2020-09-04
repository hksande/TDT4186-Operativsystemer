package sushiBar;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a waiting area used as the bounded buffer, in the producer/consumer problem.
 */
public class WaitingArea {

    /**
     * Creates a new waiting area.
     *
     * @param size The maximum number of Customers that can be waiting.
     */

    private int size;
    private List<Customer> waitingLine = new ArrayList<Customer>();

    public WaitingArea(int size) {
        // TODO Implement required functionality
        this.size = size;
    }

    /**
     * This method should put the customer into the waitingArea
     *
     * @param customer A customer created by Door, trying to enter the waiting area
     */
    public synchronized void enter(Customer customer) throws InterruptedException{
        // TODO Implement required functionality
        while (isFull()) {
            wait();
        }
        this.waitingLine.add(customer);
        SushiBar.write(Thread.currentThread().getName()+": Customer "+customer.getCustomerID()+" is waiting.");
        notifyAll();
    }

    /**
     * @return The customer that is first in line.
     */
    public synchronized Customer next() throws InterruptedException{
        // TODO: Implement required functionality
        while (isEmpty()) {
            wait();
        }
        Customer next = this.waitingLine.get(0);
        this.waitingLine.remove(0); // Needs to be removed from queue afterward
        SushiBar.write(Thread.currentThread().getName()+ ": Customer "+ next.getCustomerID()+ " is fetched.");
        notifyAll();

        return next;
    }

    // Validator functions

    public boolean isEmpty() {
        return this.waitingLine.size() == 0;
    }

    public boolean isFull() {
        return (this.waitingLine.size() >= this.size);
    }

}

