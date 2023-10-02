package interceptor

import Block
import kotlin.reflect.KClass

class StatisticsInterceptor: Interceptor {
    private val statisticsMap = mutableMapOf<KClass<*>, Int>()

    override fun intercept(block: Block, isRan: Boolean) {
        if(!isRan)
            return
        val kClass = block::class
       if(statisticsMap.containsKey(kClass))
           statisticsMap[kClass] = statisticsMap[kClass]!! + 1
        else statisticsMap[kClass] = 1
    }

    fun getStatistics(): Map<String, Int> = statisticsMap.mapKeys { it.key.simpleName!! }
}
