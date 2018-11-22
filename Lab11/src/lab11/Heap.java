package lab11;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * TODO class declaration
 * The Heap can accept any type which is comparable to itself (i.e., T should be a Comparable of T)
 *
 * @param <T>
 */
public class Heap<T extends Comparable> {
    private ArrayList<T> container;

    public Heap() {
        container = new ArrayList<>();
    }

    /**
     * TODO
     *
     * @return If size is 0, throw IllegalStateException. Otherwise, return the first element
     */
    public T peek() {
        if (size() == 0) {
            throw new IllegalStateException();
        }

        return container.get(0);
    }

    /**
     * TODO
     *
     * @return If size is 0, throw IllegalStateException. Otherwise, temporarily save the first element.
     * Afterwards, set the first position to the last element, and remove the last element.
     * Call heapifyDown(), then return the original first element
     */
    public T poll() {
        if (size() == 0) {
            throw new IllegalStateException();
        }

        T out = container.get(0);
        container.set(0, container.get(size()-1));
        container.remove(size()-1);
        heapifyDown();
        return out;
    }

    private void heapifyDown() {
        int pos = 0;
        while (hasLeft(pos)) {
            int smallerChildIndex = getLeftIndex(pos);
            if (hasRight(pos) && container.get(getRightIndex(pos)).compareTo(container.get(getLeftIndex(pos))) < 0) {
                smallerChildIndex = getRightIndex(pos);
            }
            if (container.get(pos).compareTo(container.get(smallerChildIndex)) < 0) {
                break;
            } else {
                swap(pos, smallerChildIndex);
            }
            pos = smallerChildIndex;
        }
    }

    /**
     * TODO
     * Add the object into the arraylist, then call heapifyUp()
     *
     * @param obj the object to add
     */
    public void add(T obj) {
        container.add(obj);
        heapifyUp();
    }

    public void addAll(Collection<T> list) {
        list.forEach(this::add);
    }

    /**
     * TODO
     * While the last element has a parent and is smaller than its parent, swap the two elements. Then, check again
     * with the new parent until there's either no parent or we're larger than our parent.
     */
    private void heapifyUp() {
        int pos = size()-1;
        while (hasParent(pos) && getParentIndex(pos) < pos) {
            int parent = getParentIndex(pos);
            if (container.get(parent).compareTo(container.get(pos)) > 0) {
                swap(pos, parent);
                pos = parent;
            } else {
                break;
            }
        }
    }

    public int size() {
        return container.size();
    }

    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    private int getLeftIndex(int i) {
        return 2 * i + 1;
    }

    private int getRightIndex(int i) {
        return 2 * i + 2;
    }

    private boolean hasParent(int i) {
        return getParentIndex(i) >= 0;
    }

    private boolean hasLeft(int i) {
        return getLeftIndex(i) < container.size();
    }

    private boolean hasRight(int i) {
        return getRightIndex(i) < container.size();
    }

    private void swap(int i1, int i2) {
        Collections.swap(container, i1, i2);
    }
}