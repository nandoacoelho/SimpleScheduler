package SimpleScheduler;

/**
 * SimpleScheduler.Scheduler.java
 *
 * This class is a simple round-robin scheduler.
 * The idea for this scheduler came from "Java Threads"
 * by Oaks and Wong (Oreilly, 1999).
 *
 * @author Greg Gagne, Peter Galvin, Avi Silberschatz
 * @version 1.0 - July 15, 1999.
 * Copyright 2000 by Greg Gagne, Peter Galvin, Avi Silberschatz
 * Applied Operating Systems Concepts - John Wiley and Sons, Inc.
 */

public class Scheduler extends Thread
{
    private CircularList queue;
    private int timeSlice;
    private static final int DEFAULT_TIME_SLICE = 1000; // 1 second
    private boolean sleeping;


    public Scheduler() {
        timeSlice = DEFAULT_TIME_SLICE;
        queue = new CircularList();
        sleeping = false;
    }

    public Scheduler(int quantum) {
        timeSlice = quantum;
        queue = new CircularList();
    }

    public boolean isSleeping() {
        return sleeping;
    }

    /**
     * adds a thread to the queue
     * @return void
     */
    public void addThread(Thread t) {
        queue.addItem(t);
    }

    /**
     * this method puts the scheduler to sleep for a time quantum
     * @return void
     */
    private void schedulerSleep() throws InterruptedException {
        synchronized(this)
        {

            sleeping = true;
            wait(timeSlice);
            sleeping = false;
        }
    }

    private void threadStarter() throws InterruptedException {
        for (int i = 0; i < queue.getList().size(); i++) {
            queue.getList().get(i).start();
            //queue.getList().get(i).wait();
        }
    }


    public void run() {
        TestThread current;
        try {
            threadStarter();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!queue.isEmpty())
        {
            try
            {
                current = (TestThread)queue.getNext();

                if ((current != null) )//&& (current.isAlive()))
                {
                    System.out.println(" dispatching " + current);
                    synchronized (current){
                        current.notify();
                    }

                    schedulerSleep();

                    System.out.println("* * * Context Switch * * * ");
                    System.out.println(" preempting " + current);
                    synchronized (current){
                        current.interrupt();
                    }
                }
            } catch (NullPointerException e3) { } catch (InterruptedException e) {
                    e.printStackTrace();
            }
        }
    }

}

