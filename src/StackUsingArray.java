import java.util.Arrays;

public class StackUsingArray <T> {
    private final T[] array;
    private int size;
    private final int capacity;

    @SuppressWarnings("unchecked")
    public StackUsingArray(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
        this.size = 0;
    }

    // Push an element onto the stack
    public void push(T item) {
        if (size >= capacity) {
            // Resize the array or throw an exception when it's full
            throw new IllegalStateException("Stack is full");
        }
        array[size] = item;
        size++;
    }

    // Pop an element from the stack
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        size--;
        T item = array[size];
        array[size] = null; // Optional: Set the reference to null to allow for garbage collection
        return item;
    }

    // Peek at the top element without removing it
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return array[size - 1];
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Get the size of the stack
    public int size() {
        return size;
    }

    public void clear() {
        // Set each element to null to clear the array
        Arrays.fill(array, null);
        size = 0; // Reset the size to 0
    }
}
