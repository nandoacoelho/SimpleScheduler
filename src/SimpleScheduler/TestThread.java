package SimpleScheduler;

/**
 * SimpleScheduler.TestThread.java
 * 
 * This thread is used to demonstrate how the scheduler operates.
 * This thread runs forever, periodically displaying its name.
 *
 * @author Greg Gagne, Peter Galvin, Avi Silberschatz
 * @version 1.0 - July 15, 1999.
 * Copyright 2000 by Greg Gagne, Peter Galvin, Avi Silberschatz
 * Applied Operating Systems Concepts - John Wiley and Sons, Inc.
 */


import java.util.*;

class TestThread extends Thread
{
    //private Scheduler CPUScheduler = new Scheduler();
    private String id;
    private Random r;
    private int burstTime;
    private final Scheduler scheduler;


    public TestThread(String id, Scheduler scheduler) {
        this.id = id;
        this.scheduler = scheduler;
    }

    public String toString () {
        return id;
    }

    public void run()
    {
        try {
            synchronized (this) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*
         * The thread does something
         **/
        while (true) {
            if (interrupted()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            burstTime = (int) (Math.random() * 1000);
            for (int i = 0; i < burstTime; i++)
            {
                try {
                    //Prevent overusing CPU by sleeping briefly
                    Thread.sleep(100);
                    System.out.println(id + " in loop " + i);
                } catch (InterruptedException ignored) {
                    try {
                        synchronized (this) {
                        wait();

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(scheduler.isSleeping())
            {
                synchronized (scheduler)
                {
                    System.out.println(id + " done with its CPU burst.");
                    System.out.println(id + " woke up scheduler at end of burst.");
                    scheduler.notify();
                    burstTime = (int) (Math.random() * 100);
                }
            }
        }
    }
}
