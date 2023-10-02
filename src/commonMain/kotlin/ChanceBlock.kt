open class Block(val code: () -> Unit, val chance: Double = Configuration.ALWAYS_CHANCE)

class UsuallyBlock(code: () -> Unit) : Block(code, Configuration.USUALLY_CHANCE)

class SometimesBlock(code: () -> Unit) : Block(code, Configuration.SOMETIMES_CHANCE)

class RarelyBlock(code: () -> Unit) : Block(code, Configuration.RARELY_CHANCE)
