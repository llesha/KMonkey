import interceptor.Interceptor

open class BlockSequence(val blocks: MutableList<Block> = mutableListOf()) {
    private val interceptors = mutableListOf<Interceptor>()

    fun block(configuration: () -> Unit) {
        blocks.add(Block(configuration))
    }

    fun usually(configuration: () -> Unit) {
        blocks.add(UsuallyBlock(configuration))
    }

    fun sometimes(configuration: () -> Unit) {
        blocks.add(SometimesBlock(configuration))
    }

    fun rarely(configuration: () -> Unit) {
        blocks.add(RarelyBlock(configuration))
    }

    /**
     * Add interceptor that executes before all added ones
     */
    fun addInterceptor(interceptor: Interceptor) {
        interceptors.add(interceptor)
    }

    /**
     * Adds interceptor that executes after all already added ones
     */
    fun addInterceptorAtEnd(interceptor: Interceptor) {
        interceptors.add(0, interceptor)
    }

    open fun run() {
        for (block in blocks) {
            val isRan = block.chance > Configuration.rnd.nextDouble()
            for (i in interceptors.lastIndex downTo 0) {
                interceptors[i].intercept(block, isRan)
            }
            if(isRan) {
                block.code()
            }
        }
    }
}

class EveryVariant(blocks: MutableList<Block> = mutableListOf()) : BlockSequence(blocks) {
    fun createIterator(): Iterator<BooleanArray> {
        return object : Iterator<BooleanArray> {
            val runOptions = BooleanArray(blocks.size) { false }
            val end = BooleanArray(blocks.size) { true }

            override fun hasNext(): Boolean {
                return !runOptions.contentEquals(end)
            }

            override fun next(): BooleanArray {
                var i = 0
                while (true) {
                    runOptions[i] = runOptions[i].not()
                    if (runOptions[i]) {
                        return runOptions
                    }
                    i++
                }
            }
        }
    }

    override fun run() {
        var runOptions: BooleanArray?
        val iterator = createIterator()
        while (iterator.hasNext()) {
            runOptions = iterator.next()
            runOptions.withIndex().forEach { (i, value) ->
                if (value)
                    blocks[i].code()
            }
        }
    }
}

/**
 * Could be replaced by loop or kotlin repeat.
 * Saved for later, might be able to skip same runs (now it seems that it will consume much memory)
 */
class SomeVariants(val quantity: Int, blocks: MutableList<Block> = mutableListOf()) : BlockSequence(blocks) {
    override fun run() {
        repeat(quantity) {
            super.run()
        }
    }
}
