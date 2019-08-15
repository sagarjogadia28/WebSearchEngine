package heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Heapify {

    private PriorityQueue<Integer> priorityQueue;

    //If minHeap is true then priority queue will implement minHeap else maxHeap
    public Heapify(boolean minHeap) {
        if (minHeap)
            priorityQueue = new PriorityQueue<>();
        else
            priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
    }

    //Insert all the elements of the list in the heap
    public void insertElements(ArrayList<Integer> list) {
        priorityQueue.addAll(list);
    }

    //Get the top element
    public int getElement() {
        return priorityQueue.peek();
    }
    //Get and remove the top element
    public int removeAndGetElement() {
        return priorityQueue.poll();
    }

    public boolean hasElement(){
        return !priorityQueue.isEmpty();
    }

}
