import interceptor.StatisticsInterceptor
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TestBlocks {
    @Test
    fun simpleBlockTest() {
        val statisticsInterceptor = StatisticsInterceptor()
        repeat(100) {
            var result = 1
            blocks {
                block { result *= 2 }
                usually { result *= 3; println("Usually-block called") }
                sometimes { result *= 5; println("Sometimes-block called") }
                rarely { result *= 7; println("Rarely-block called") }
                addInterceptor(statisticsInterceptor)
            }
            assertTrue(result % 2 == 0)
        }
        println(statisticsInterceptor.getStatistics())
    }

    @Test
    fun testCustomChanceBlocks() {
        var result = 1
        blocks {
            chance(1.0) { result *= 2 }
            chance(0.5) { result *= 3 }
            chance(0.0) { result *= 5 }
        }
        assertTrue(result % 2 == 0)
        assertFalse(result % 5 == 0)
    }
}