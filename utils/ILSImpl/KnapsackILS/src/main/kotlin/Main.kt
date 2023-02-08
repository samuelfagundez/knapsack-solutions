fun main(args: Array<String>) {
    val items = listOf(
        Item(weight = 10, value = 60),
        Item(weight = 20, value = 100),
        Item(weight = 30, value = 120),
    )

    val knapsack = Knapsack(items, maxWeight = 30)
    val solution = knapsack.solve()

    println(solution.sumOf { total -> total.value })
}
