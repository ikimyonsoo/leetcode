package Heaps;

import java.util.PriorityQueue;

public class Solution {

    private PriorityQueue minHeap; 
    private PriorityQueue maxHeap; 

    public void insert(int x) {
        maxHeap.add(x);
        minHeap.add(maxHeap.poll());

        if (minHeap.size() > maxHeap.size()) maxHeap.add(minHeap.poll());

    }

    public double getMedian() {
        if (minHeap.size() == maxHeap) return (maxHeap.poll() + minHeap.poll()) / 2.0;
        return maxHeap.peek(); 
    }
    
}
