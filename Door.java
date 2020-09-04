package sushiBar;

import java.util.Random;

/**
 * This class implements the Door component of the sushi bar
 * assignment The Door corresponds to the Producer in the
 * producer/consumer problem
 */
public class Door implements Runnable {

    /**
     * Creates a new Door. Make sure to save the
     * @param waitingArea   The customer queue waiting for a seat
     */

    private WaitingArea waitingArea;
    private Random random;


    public Door(WaitingArea waitingArea) {
        // TODO: Implement required functionality.
        this.waitingArea = waitingArea;
        this.random = new Random();
    }

    /**
     * This method will run when the door thread is created (and started).
     * The method should create customers at random intervals and try to put them in the waiting area.
     */
    @Override
    public void run() {
        // TODO: Implement required functionality.
        while(isOpen()) {
            int randomWaitingTime = random.nextInt(SushiBar.doorWait) + 1;
            try {
                Thread.sleep(randomWaitingTime);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            Customer customer = new Customer();
            try {
                this.waitingArea.enter(customer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        SushiBar.write(Thread.currentThread().getName()+": ***** DOOR CLOSED *****");
    }

    // Add more methods as you see fit

    // Validators

    public boolean isOpen(){
        return SushiBar.isOpen;
    }
}
