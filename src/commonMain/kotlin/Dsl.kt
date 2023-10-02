fun all(configuration: EveryVariant.() -> Unit) {
    val everyVariant = EveryVariant()
    everyVariant.apply(configuration)
    everyVariant.run()
}

fun some(quantity: Int, configuration: SomeVariants.() -> Unit) {
    val someVariants = SomeVariants(quantity)
    someVariants.apply(configuration)
    someVariants.run()
}

fun blocks(configuration: BlockSequence.() -> Unit) {
    val blocks = BlockSequence()
    blocks.apply(configuration)
    blocks.run()
}
