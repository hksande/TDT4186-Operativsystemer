package sushiBar;
/**
 * This class implements the consumer part of the producer/consumer problem.
 * One waitress instance corresponds to one consumer.
 */
public class Waitress implements Runnable {

    /**
     * Creates a new waitress. Make sure to save the parameter in the class
     *
     * @param waitingArea The waiting area for customers
     */

    WaitingArea waitingArea;

    Waitress(WaitingArea waitingArea) {
        // TODO: Implement required functionality.
        this.waitingArea = waitingArea;
    }

    /**
     * This is the code that will run when a new thread is
     * created for this instance
     */
    @Override
    public void run() {
        // TODO: Implement required functionality.
    	while (SushiBar.isOpen || !waitingArea.isEmpty()) {  // Så lenge det er åpent eller er kunder igjen.
            Customer next = null;
            try {
                next = this.waitingArea.next();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(SushiBar.waitressWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                assert next != null;
                next.order(); // Allow the customer to make the order
                SushiBar.write(Thread.currentThread().getName()+ ": Customer "+ next.getCustomerID()+ " is leaving.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

