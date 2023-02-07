class Knapsack(private val items: List<Item>, private val maxWeight: Int) {

    fun solve(): List<Item> {
        var currentSolution = generateInitialSolution()
        var currentValue = evaluate(currentSolution)

        var bestSolution = currentSolution
        var bestValue = currentValue

        while (true) {
            val neighborhood = generateNeighborhood(currentSolution)
            val neighbor = neighborhood.maxBy { evaluate(it) } ?: break

            if (evaluate(neighbor) > currentValue) {
                currentSolution = neighbor
                currentValue = evaluate(neighbor)
            } else if (evaluate(neighbor) > bestValue) {
                bestSolution = neighbor
                bestValue = evaluate(neighbor)
            } else {
                break
            }
        }

        return bestSolution
    }

    private fun generateInitialSolution(): List<Item> {
        return items.filter { it.weight <= maxWeight }
    }

    private fun generateNeighborhood(currentSolution: List<Item>): List<List<Item>> {
        return List(currentSolution.size) { index ->
            currentSolution.toMutableList().apply { removeAt(index) }
        }
    }

    private fun evaluate(solution: List<Item>): Int {
        return solution.sumOf { it.value }
    }
}
