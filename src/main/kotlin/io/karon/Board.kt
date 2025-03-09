package io.karon

interface Board {
	val state: Array<IntArray>
	fun hasSameState(other: Board): Boolean
	fun forEachCell(block: (Int, Int, Int) -> Unit)
	fun nextBoard(): Board
}
