import java.math.BigInteger

val MEMOIZER = mutableMapOf<BigInteger, BigInteger>()

/**
 * Calculates the nth Fibonacci number using memoization to improve performance.
 *
 * @param i The index of the Fibonacci number to calculate.
 * @return The nth Fibonacci number.
 */
fun fibonacciMemoized(i: BigInteger): BigInteger {
    val key = i
    if (key in MEMOIZER) {
        return MEMOIZER[key]!!
    }

    if (i == BigInteger.valueOf(0)) {
        return BigInteger.valueOf(0)
    } else if (i == BigInteger.valueOf(1)) {
        return BigInteger.valueOf(1)
    }

    val result = fibonacciMemoized(i - BigInteger.valueOf(1)) + fibonacciMemoized(i - BigInteger.valueOf(2))

    MEMOIZER[key] = result

    return result
}

/**
 * Calculates the nth Fibonacci number using recursion.
 *
 * @param i The index of the Fibonacci number to calculate.
 * @return The nth Fibonacci number.
 */
fun fibonacci(i: BigInteger): BigInteger {
    if (i == BigInteger.valueOf(0)) {
        return BigInteger.valueOf(0)
    } else if (i == BigInteger.valueOf(1)) {
        return BigInteger.valueOf(1)
    }
    return fibonacci(i - BigInteger.valueOf(1)) + fibonacci(i - BigInteger.valueOf(2))
}

/**
 * Measures the execution time of a given function and returns the result along with the formatted
 * execution time.
 *
 * @param function The lambda function to be executed and measured.
 * @return A Pair containing the execution time in nanoseconds and a formatted string displaying the result
 *         and execution time.
 */
fun <T> measureExecutionTime(function: () -> T): Pair<Long, String> {
    val startTime = System.nanoTime()
    val result = function()
    val executionTime = System.nanoTime() - startTime
    return Pair(
        executionTime,
        "Result: $result \n" +
            "Execution Time: $executionTime nanoseconds \n" +
            "- - - - - - -"
    )
}

/**
 * Formats the execution time in nanoseconds to a human-readable string in the format "mm:ss:SSS:NNNNNN".
 *
 * @param executionTimeInNanos The execution time in nanoseconds.
 * @return The formatted execution time as a human-readable string.
 */
fun formatExecutionTime(executionTimeInNanos: Long): String {
    val milliseconds = executionTimeInNanos / 1_000_000
    val seconds = milliseconds / 1000
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    val remainingMilliseconds = milliseconds % 1000
    val remainingNanoseconds = executionTimeInNanos % 1_000_000

    return String.format("%02d:%02d:%03d:%06d", minutes, remainingSeconds, remainingMilliseconds, remainingNanoseconds)
}

fun main() {
    var avgExecNormal: Long = 0
    var avgExecMemoized: Long = 0
    var totalExecNormal: Long = 0
    var totalExecMemoized: Long = 0
    val numIterations = 40L

    for (i in 1L..numIterations) {
        val (execTimeNormal, stringNormal) = measureExecutionTime { fibonacci(BigInteger.valueOf(i)) }
        val (execTimeMemoized, stringMemoized) = measureExecutionTime { fibonacciMemoized(BigInteger.valueOf(i)) }
        println("Fibonacci($i) using recursion:")
        println(stringNormal)
        println("Fibonacci($i) using memoization:")
        println(stringMemoized)

        totalExecNormal += execTimeNormal
        totalExecMemoized += execTimeMemoized
    }

    avgExecNormal = totalExecNormal / numIterations
    avgExecMemoized = totalExecMemoized / numIterations

    println("Average Execution Time for Normal Fibonacci: ${formatExecutionTime(avgExecNormal)}")
    println("Average Execution Time for Memoized Fibonacci: ${formatExecutionTime(avgExecMemoized)}")
    println("- - - - - - -")
    println("Total Execution Time for Normal Fibonacci: ${formatExecutionTime(totalExecNormal)}")
    println("Total Execution Time for Normal Fibonacci: ${formatExecutionTime(totalExecMemoized)}")
}
