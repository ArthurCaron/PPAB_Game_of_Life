package io.karon

import kotlin.random.Random
import kotlin.random.nextInt

class SimpleBoard(
	val height: Int,
	val width: Int,
	computeCellValue: (Int, Int) -> Int = { _, _ -> 0 }
) : Board {
	constructor(initialState: Array<IntArray>) : this(
		initialState.size,
		initialState[0].size,
		{ i, j -> initialState[i][j]}
	)

	override val state: Array<IntArray> =
		Array(height) { lineIndex ->
			IntArray(width) { cellIndex ->
				computeCellValue(lineIndex, cellIndex)
			}
		}

	override fun hasSameState(other: Board) = state.contentDeepEquals(other.state)

	override fun forEachCell(block: (Int, Int, Int) -> Unit) {
		(0 until height).map { i ->
			(0 until width).map { j ->
				block(i, j, state[i][j])
			}
		}
	}

	override fun nextBoard(): Board {
		val nextBoard = SimpleBoard(height, width)
		nextBoard.fillState { i, j ->
			val numberOfNeighbors = getNeighbors(i, j)
				.filter { state[it.first][it.second] == 1 }
				.count()
			nextValue(state[i][j], numberOfNeighbors)
		}
		return nextBoard
	}

	private fun getNeighbors(i: Int, j: Int): List<Pair<Int, Int>> {
		val iRange = ((i - 1)..(i + 1)).coerceInRange(0 until height)
		val jRange = ((j - 1)..(j + 1)).coerceInRange(0 until width)

		return iRange.map { x ->
			jRange.map { y ->
				x to y
			}
		}.flatten()
			.filterNot { it.first == i && it.second == j }
	}

	private fun nextValue(currentValue: Int, numberOfNeighbors: Int): Int {
		return if (currentValue == 1 && numberOfNeighbors in (2..3)) {
			1
		} else if (currentValue == 0 && numberOfNeighbors == 3) {
			1
		} else {
			0
		}
	}

	fun kill() = fillState { _, _ -> 0 }

	fun randomize(chanceForLife: Int) = fillState { _, _ -> if (Random.nextInt((1..100)) <= chanceForLife) 1 else 0 }

	private fun fillState(computeCellValue: (Int, Int) -> Int): Board {
		(0 until height).map { i ->
			(0 until width).map { j ->
				state[i][j] = computeCellValue(i, j)
			}
		}
		return this
	}
}
