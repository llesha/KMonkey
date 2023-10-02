import kotlin.random.Random

object Configuration {
    const val ALWAYS_CHANCE: Double = 1.0
    var USUALLY_CHANCE: Double = 0.8
    var SOMETIMES_CHANCE: Double = 0.2
    var RARELY_CHANCE: Double = 0.05
    var rnd = Random
}