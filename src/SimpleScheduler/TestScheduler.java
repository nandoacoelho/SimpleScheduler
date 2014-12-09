package SimpleScheduler;

/**
 * SimpleScheduler.TestScheduler.java
 *
 * This program demonstrates how the scheduler operates.
 * This creates the scheduler and then the three example threads.
 *
 * @author Greg Gagne, Peter Galvin, Avi Silberschatz
 * @version 1.0 - July 15, 1999.
 * Copyright 2000 by Greg Gagne, Peter Galvin, Avi Silberschatz
 * Applied Operating Systems Concepts - John Wiley and Sons, Inc.
 */

public class TestScheduler
{
    public static void main(String args[]) {
        /**
         * This must run at the highest priority to ensure that
         * it can create the scheduler and the example threads.
         * If it did not run at the highest priority, it is possible
         * that the scheduler could preempt this and not allow it to
         * create the example threads.
         */
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        Scheduler CPUScheduler = new Scheduler();

        TestThread t1 = new TestThread("Thread 1", CPUScheduler);
        TestThread t2 = new TestThread("Thread 2", CPUScheduler);
        TestThread t3 = new TestThread("Thread 3", CPUScheduler);


        CPUScheduler.addThread(t1);
        CPUScheduler.addThread(t2);
        CPUScheduler.addThread(t3);

        CPUScheduler.start();
    }
}
