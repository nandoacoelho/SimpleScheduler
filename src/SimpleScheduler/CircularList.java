package SimpleScheduler;

/**
 * SimpleScheduler.CircularList.java
 *
 * This class implements a circular list using the Vector class
 * note that elements in a vector with n elements are numbered 0 .. (n-1)
 *
 * @author Greg Gagne, Peter Galvin, Avi Silberschatz
 * @version 1.0 - July 15, 1999.
 * Copyright 2000 by Greg Gagne, Peter Galvin, Avi Silberschatz
 * Applied Operating Systems Concepts - John Wiley and Sons, Inc.
 */

import java.util.*;

public class CircularList 
{
    private ArrayList<Thread> List;
    private int index;

    public int getIndex() {
        return index;
    }

    public CircularList() {
        List = new ArrayList<Thread>(10);
        index = 0;
    }

    public ArrayList<Thread> getList() {
        return List;
    }

    /**
     * this method returns the next element in the list.
     * @return Object
     */
    public Object getNext() {
        Object nextElement = null;
        int lastElement;

        if (!List.isEmpty() ) {
            if (index == List.size() )
                index = 0;

            nextElement = List.get(index);

            ++index;
        }

        return nextElement;
    }

    public boolean isEmpty ()
    {
        return List.isEmpty();
    }

    /**
     * this method adds an item to the list
     * @return void
     */
    public void addItem(Thread t) {
        List.add(t);
    }

}
