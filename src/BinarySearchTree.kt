open class BinarySearchTree {
    data class Node(var value: Int) {
        var left: Node? = null
        var right: Node? = null
        var height: Int = 0

    }

    private var root: Node? = null

    open fun insert(value: Int) {
        root = insertRec(root, value)
    }

    private fun insertRec(node: Node?, value: Int): Node {
        if (node == null) {
            return Node(value)
        }

        if (value < node.value) {
            node.left = insertRec(node.left, value)
        } else if (value > node.value) {
            node.right = insertRec(node.right, value)
        }

        return node
    }

    fun search(value: Int): Boolean {
        return searchRec(root, value)
    }

    private fun searchRec(node: Node?, value: Int): Boolean {
        if (node == null) {
            return false
        }

        if (value == node.value) {
            return true
        }

        return if (value < node.value) {
            searchRec(node.left, value)
        } else {
            searchRec(node.right, value)
        }
    }

    fun delete(value: Int) {
        root = deleteRec(root, value)
    }

    private fun deleteRec(root: Node?, value: Int): Node? {
        if (value < root!!.value) {
            root.left = deleteRec(root.left, value)
        } else if (value > root.value) {
            root.right = deleteRec(root.right, value)
        } else {
            if (root.left == null) {
                return root.right
            } else if (root.right == null) {
                return root.left
            }

            root.value = minValue(root.right)
            root.right = deleteRec(root.right, root.value)
        }
        return root
    }

    private fun minValue(node: Node?): Int {
        var minValue = node?.value ?: Int.MAX_VALUE
        var current = node
        while (current?.left != null) {
            minValue = current.left?.value ?: Int.MAX_VALUE
            current = current.left
        }
        return minValue
    }

    fun printTree() {
        inOrderTraversal(root)
    }

    private fun inOrderTraversal(node: Node?) {
        if (node != null) {
            inOrderTraversal(node.left)
            println(node.value)
            inOrderTraversal(node.right)
        }
    }

    fun inorderTraversalRecursiveLNR(): List<Int> {
        val result = mutableListOf<Int>()
        inorderTraversalRecursiveLNR(root, result)
        return result
    }

    private fun inorderTraversalRecursiveLNR(node: Node?, result: MutableList<Int>) {
        if (node != null) {
            inorderTraversalRecursiveLNR(node.left, result)
            result.add(node.value)
            inorderTraversalRecursiveLNR(node.right, result)
        }
    }

    fun preorderTraversalRecursiveNLR(): List<Int> {
        val result = mutableListOf<Int>()
        preorderTraversalRecursiveNLR(root, result)
        return result
    }

    private fun preorderTraversalRecursiveNLR(node: Node?, result: MutableList<Int>) {
        if (node != null) {
            result.add(node.value)
            preorderTraversalRecursiveNLR(node.left, result)
            preorderTraversalRecursiveNLR(node.right, result)
        }
    }

    fun postorderTraversalRecursiveLRN(): List<Int> {
        val result = mutableListOf<Int>()
        postorderTraversalRecursiveLRN(root, result)
        return result
    }

    private fun postorderTraversalRecursiveLRN(node: Node?, result: MutableList<Int>) {
        if (node != null) {
            postorderTraversalRecursiveLRN(node.left, result)
            postorderTraversalRecursiveLRN(node.right, result)
            result.add(node.value)
        }
    }

    fun levelOrderTraversalRecursive(): List<Int> {
        val result = mutableListOf<Int>()
        val height = getHeight(root)
        for (i in 1..height) {
            levelOrderTraversalRecursive(root, i, result)
        }
        return result
    }

    private fun levelOrderTraversalRecursive(node: Node?, level: Int, result: MutableList<Int>) {
        if (node == null) return
        if (level == 1) {
            result.add(node.value)
        } else if (level > 1) {
            levelOrderTraversalRecursive(node.left, level - 1, result)
            levelOrderTraversalRecursive(node.right, level - 1, result)
        }
    }

    private fun getHeight(node: Node?): Int {
        if (node == null) return 0
        val leftHeight = getHeight(node.left)
        val rightHeight = getHeight(node.right)
        return maxOf(leftHeight, rightHeight) + 1
    }

    fun inorderTraversalIterativeLNR(): List<Int> {
        val result = mutableListOf<Int>()
        val stack = mutableListOf<Node>()
        var current = root
        while (current != null || stack.isNotEmpty()) {
            while (current != null) {
                stack.add(current)
                current = current.left
            }
            current = stack.removeAt(stack.size - 1)
            result.add(current.value)
            current = current.right
        }
        return result
    }

    fun preorderTraversalIterativeNLR(): List<Int> {
        val result = mutableListOf<Int>()
        val stack = mutableListOf<Node>()
        if (root != null) {
            stack.add(root!!)
        }
        while (stack.isNotEmpty()) {
            val current = stack.removeAt(stack.size - 1)
            result.add(current.value)
            if (current.right != null) {
                stack.add(current.right!!)
            }
            if (current.left != null) {
                stack.add(current.left!!)
            }
        }
        return result
    }

    fun postorderTraversalIterativeLRN(): List<Int> {
        val result = mutableListOf<Int>()
        val stack1 = mutableListOf<Node>()
        val stack2 = mutableListOf<Node>()
        if (root != null) {
            stack1.add(root!!)
        }
        while (stack1.isNotEmpty()) {
            val current = stack1.removeAt(stack1.size - 1)
            stack2.add(current)
            if (current.left != null) {
                stack1.add(current.left!!)
            }
            if (current.right != null) {
                stack1.add(current.right!!)
            }
        }
        while (stack2.isNotEmpty()) {
            result.add(stack2.removeAt(stack2.size - 1).value)
        }
        return result
    }

    fun levelOrderTraversalIterative(): List<Int> {
        val result = mutableListOf<Int>()
        val queue = ArrayDeque<Node>()
        if (root != null) {
            queue.add(root!!)
        }
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            result.add(current.value)
            if (current.left != null) {
                queue.add(current.left!!)
            }
            if (current.right != null) {
                queue.add(current.right!!)
            }
        }
        return result
    }
}