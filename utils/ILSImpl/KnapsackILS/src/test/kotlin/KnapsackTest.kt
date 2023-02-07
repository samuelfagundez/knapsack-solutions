import org.testng.annotations.Test
import kotlin.test.assertEquals

class KnapsackTest {
    @Test
    fun testSolve() {
        val items = listOf(
            Item(weight = 1, value = 6),
            Item(weight = 2, value = 10),
            Item(weight = 3, value = 12),
        )
        val knapsack = Knapsack(items, maxWeight = 5)

        val expected = listOf(
            Item(weight = 2, value = 10),
            Item(weight = 3, value = 12),
        )
        val actual = knapsack.solve()

        assertEquals(expected, actual)
    }
}
