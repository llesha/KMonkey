package interceptor

import Block

interface Interceptor {
    fun intercept(block: Block, isRan: Boolean)
}