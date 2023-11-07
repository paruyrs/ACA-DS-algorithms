import java.util.Vector

class DepthFirstSearch(adjacencyList: Map<Char, List<Int>>) {
    private val adjacencyList = adjacencyList

    fun dfs(startNode: Char) {
        val visited = mutableSetOf<Char>()

        dfsRecursive(startNode, visited)
    }

    private fun dfsRecursive(node: Char, visited: MutableSet<Char>) {
        visited.add(node)
        print("$node ")
        val x: Vector<Int>

        for (neighbor in adjacencyList[node] ?: emptyList()) {
            if (neighbor !in visited) {
                dfsRecursive(neighbor, visited)
            }
        }
    }
}