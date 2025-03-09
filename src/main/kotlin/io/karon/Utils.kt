package io.karon

fun IntRange.coerceInRange(other: IntRange): IntRange {
	val min = this.first.coerceIn(other)
	val max = this.last.coerceIn(other)
	return min..max
}
