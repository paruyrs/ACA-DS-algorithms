class QueueWithStacks {
    private val capacity = 10
    private val stackFirst = StackUsingArray<Int>(capacity)
    private val stackSecond = StackUsingArray<Int>(capacity)

    fun enqueue(item: Int): Boolean {
        return if (stackFirst.size() == capacity) {
            stackFirst.push(item)
            true
        } else
            false

    }

    fun dequeue(): Int {
        if (stackSecond.isEmpty) {
            while (!stackFirst.isEmpty){
                stackSecond.push(stackFirst.pop())
            }
        }

        return stackSecond.pop()
    }

    fun isEmpty(): Boolean {
        return stackFirst.isEmpty && stackSecond.isEmpty
    }

    fun isFull(): Boolean {
        return length() >= capacity
    }

     fun length(): Int {
        return stackFirst.size() + stackSecond.size()
    }

    fun front(): Int {
        if (isEmpty()) {
            throw IllegalStateException("Queue is empty.");
        }

        if (stackSecond.isEmpty) {
            while (!stackFirst.isEmpty) {
                stackSecond.push(stackFirst.pop())
            }
        }
        return stackSecond.peek()
    }

    fun clear() {
        stackFirst.clear()
        stackSecond.clear()
    }

}