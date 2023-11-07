import kotlin.math.max

class AVLNode(var key: Int) {
    var height = 1
    var left: AVLNode? = null
    var right: AVLNode? = null
}

class AVLBinarySearchTree {
    private var root: AVLNode? = null

    private fun height(node: AVLNode?): Int {
        return node?.height ?: 0
    }

    private fun updateHeight(node: AVLNode) {
        node.height = 1 + max(height(node.left), height(node.right))
    }

    private fun balanceFactor(node: AVLNode?): Int {
        return height(node?.left) - height(node?.right)
    }

    private fun rotateLeft(z: AVLNode): AVLNode {
        val y = z.right ?: return z  // If z.right is null, no rotation is needed
        val t2 = y.left

        y.left = z
        z.right = t2

        updateHeight(z)
        updateHeight(y)

        return y
    }

    private fun rotateRight(y: AVLNode): AVLNode {
        val x = y.left ?: return y  // If y.left is null, no rotation is needed
        val t2 = x.right

        x.right = y
        y.left = t2

        updateHeight(y)
        updateHeight(x)

        return x
    }

    fun insert(key: Int) {
        root = insertNode(root, key)
    }

    private fun insertNode(node: AVLNode?, key: Int): AVLNode {
        if (node == null) {
            return AVLNode(key)
        }

        if (key < node.key) {
            node.left = insertNode(node.left, key)
        } else {
            node.right = insertNode(node.right, key)
        }

        updateHeight(node)

        val balance = balanceFactor(node)

        return when {
            balance > 1 && key < node.left!!.key -> rotateRight(node)
            balance < -1 && key > node.right!!.key -> rotateLeft(node)
            balance > 1 && key > node.left!!.key -> {
                node.left = rotateLeft(node.left!!)
                rotateRight(node)
            }
            balance < -1 && key < node.right!!.key -> {
                node.right = rotateRight(node.right!!)
                rotateLeft(node)
            }
            else -> node
        }
    }

    fun delete(key: Int) {
        root = deleteNode(root, key)
    }

    private fun deleteNode(node: AVLNode?, key: Int): AVLNode? {
        if (node == null) {
            return node
        }

        if (key < node.key) {
            node.left = deleteNode(node.left, key)
        } else if (key > node.key) {
            node.right = deleteNode(node.right, key)
        } else {
            if (node.left == null || node.right == null) {
                val temp = if (node.left != null) node.left else node.right
                return if (temp == null) {
                    null
                } else {
                    node.key = temp.key // Reassign the key using a var
                    node.left = null // Optionally set left child to null to remove the reference
                    node.right = null // Optionally set right child to null to remove the reference
                    temp
                }
            } else {
                val temp = findMin(node.right!!)
                node.key = temp.key // Reassign the key using a var
                node.right = deleteNode(node.right, temp.key)
            }
        }

        updateHeight(node)

        val balance = balanceFactor(node)

        return when {
            balance > 1 && balanceFactor(node.left) >= 0 -> rotateRight(node)
            balance > 1 && balanceFactor(node.left) < 0 -> {
                node.left = rotateLeft(node.left!!)
                rotateRight(node)
            }
            balance < -1 && balanceFactor(node.right) <= 0 -> rotateLeft(node)
            balance < -1 && balanceFactor(node.right) > 0 -> {
                node.right = rotateRight(node.right!!)
                rotateLeft(node)
            }
            else -> node
        }
    }

    private fun findMin(node: AVLNode): AVLNode {
        var current = node
        while (current.left != null) {
            current = current.left!!
        }
        return current
    }

    fun search(key: Int): AVLNode? {
        return searchNode(root, key)
    }

    private fun searchNode(node: AVLNode?, key: Int): AVLNode? {
        if (node == null || node.key == key) {
            return node
        }
        return if (key < node.key) {
            searchNode(node.left, key)
        } else {
            searchNode(node.right, key)
        }
    }
}
