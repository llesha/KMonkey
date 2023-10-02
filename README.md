Implementation of the [monkey testing](https://dannorth.net/monkey-business-value/) idea.

# How to use

Create `BlockSequence` with one of following keywords:

* `blocks` - runs all blocks once with respect to their probabilities
* `all` - run all possible block combinations. Ignores block probabilities
* `some(n: Int)` - run blocks `n` times (works like for-loop or repeat for now)

## Blocks

Each block is executed with a particular probability:

* `block` - 100% probability (always executed)
* `usually` - 80%
* `sometimes` - 20%
* `rarely` - 5%
* `chance(p: Double)` - execute with a probability of `p` (in range 0..1)

# Configuration

You can change probabilities of block execution in [Configuration class](src/commonMain/kotlin/Configuration.kt)

# Interceptors

It is possible to intercept a block call by adding an [Interceptor](src/commonMain/kotlin/interceptor/Interceptor.kt).
Method `intercept(block: Block, isRan: Boolean)` is called on each following block, regardless of whether it is ran or
not (parameter `isRan` shows just that).