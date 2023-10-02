class KMonkeyException(val blocks: List<Block>, val runOptions: BooleanArray, val exception: Exception) : Throwable() {
    override val message: String
        get() = "Run failed with options: ${
            runOptions.joinToString(separator = "") {
                if (it)
                    "1" else "0"
            }
        } and exception: " + exception.message + "; stacktrace: " + exception.stackTraceToString()
}
