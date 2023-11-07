import java.util.LinkedList;

public class StackUsingLinkedList<T> {
    private final LinkedList<T> list = new LinkedList<T>();

    // Push an element onto the stack
    public void push(T item) {
        list.addFirst(item);
    }

    // Pop an element from the stack
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return list.removeFirst();
    }

    // Peek at the top element without removing it
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return list.getFirst();
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return list.isEmpty();
    }

    // Get the size of the stack
    public int size() {
        return list.size();
    }
}
