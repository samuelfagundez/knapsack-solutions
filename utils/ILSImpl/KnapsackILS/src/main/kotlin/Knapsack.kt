class Knapsack(private val items: List<Item>, private val maxWeight: Int) {

    fun solve(): List<Item> {
        var bestSolution = generateInitialSolution()
        var bestValue = evaluate(bestSolution)

        var currentSolution = generateInitialSolution()
        var currentValue = bestValue

        while (true) {
            val neighborhood = generateNeighborhood(currentSolution)
            val neighbor = neighborhood.maxBy { evaluate(it) } ?: break
            if (evaluate(neighbor) >= currentValue) {
                currentSolution = neighbor
                currentValue = evaluate(neighbor)
            } else if (currentValue > bestValue) {
                bestSolution = currentSolution
                bestValue = currentValue
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
        val totalWeight = solution.sumOf { it.weight }
        return if (totalWeight <= maxWeight) {
            solution.sumOf { it.value }
        } else {
            0
        }
    }
}
