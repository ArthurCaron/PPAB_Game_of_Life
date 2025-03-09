package io.karon

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SimpleBoardTest {
	@Test
	fun `deadState creates empty board`() {
		val board = SimpleBoard(5, 5)
		board.forEachCell { _, _, value ->
			assertEquals(value, 0)
		}
	}

	@Test
	fun `live cell with 0 neighbors dies`() {
		val startingState = SimpleBoard(arrayOf(
			intArrayOf(0, 0, 0),
			intArrayOf(0, 1, 0),
			intArrayOf(0, 0, 0)
		))
		assertCenterCellIsDeadNextBoard(startingState)
	}

	@Test
	fun `live cell with 1 neighbor dies`() {
		val startingState = SimpleBoard(arrayOf(
			intArrayOf(1, 0, 0),
			intArrayOf(0, 1, 0),
			intArrayOf(0, 0, 0)
		))
		assertCenterCellIsDeadNextBoard(startingState)
	}

	@Test
	fun `live cell with 2 neighbors stays alive`() {
		val startingState = SimpleBoard(arrayOf(
			intArrayOf(1, 1, 0),
			intArrayOf(0, 1, 0),
			intArrayOf(0, 0, 0)
		))
		assertCenterCellIsAliveNextBoard(startingState)
	}

	@Test
	fun `live cell with 3 neighbors stays alive`() {
		val startingState = SimpleBoard(arrayOf(
			intArrayOf(1, 1, 1),
			intArrayOf(0, 1, 0),
			intArrayOf(0, 0, 0)
		))
		assertCenterCellIsAliveNextBoard(startingState)
	}

	@Test
	fun `live cell with 4 neighbors dies`() {
		val startingState = SimpleBoard(arrayOf(
			intArrayOf(1, 1, 1),
			intArrayOf(1, 1, 0),
			intArrayOf(0, 0, 0)
		))
		assertCenterCellIsDeadNextBoard(startingState)
	}

	@Test
	fun `live cell with 5 neighbors dies`() {
		val startingState = SimpleBoard(arrayOf(
			intArrayOf(1, 1, 1),
			intArrayOf(1, 1, 1),
			intArrayOf(0, 0, 0)
		))
		assertCenterCellIsDeadNextBoard(startingState)
	}

	@Test
	fun `live cell with 6 neighbors dies`() {
		val startingState = SimpleBoard(arrayOf(
			intArrayOf(1, 1, 1),
			intArrayOf(1, 1, 1),
			intArrayOf(1, 0, 0)
		))
		assertCenterCellIsDeadNextBoard(startingState)
	}

	@Test
	fun `live cell with 7 neighbors dies`() {
		val startingState = SimpleBoard(arrayOf(
			intArrayOf(1, 1, 1),
			intArrayOf(1, 1, 1),
			intArrayOf(1, 1, 0)
		))
		assertCenterCellIsDeadNextBoard(startingState)
	}

	@Test
	fun `live cell with 8 neighbors dies`() {
		val startingState = SimpleBoard(arrayOf(
			intArrayOf(1, 1, 1),
			intArrayOf(1, 1, 1),
			intArrayOf(1, 1, 1)
		))
		assertCenterCellIsDeadNextBoard(startingState)
	}

	@Test
	fun `dead cell with 0 neighbors stays dead`() {
		val startingState = SimpleBoard(arrayOf(
			intArrayOf(0, 0, 0),
			intArrayOf(0, 0, 0),
			intArrayOf(0, 0, 0)
		))
		assertCenterCellIsDeadNextBoard(startingState)
	}

	@Test
	fun `dead cell with 1 neighbor stays dead`() {
		val startingState = SimpleBoard(arrayOf(
			intArrayOf(1, 0, 0),
			intArrayOf(0, 0, 0),
			intArrayOf(0, 0, 0)
		))
		assertCenterCellIsDeadNextBoard(startingState)
	}

	@Test
	fun `dead cell with 2 neighbors stays dead`() {
		val startingState = SimpleBoard(arrayOf(
			intArrayOf(1, 1, 0),
			intArrayOf(0, 0, 0),
			intArrayOf(0, 0, 0)
		))
		assertCenterCellIsDeadNextBoard(startingState)
	}

	@Test
	fun `dead cell with 3 neighbors becomes alive`() {
		val startingState = SimpleBoard(arrayOf(
			intArrayOf(1, 1, 1),
			intArrayOf(0, 0, 0),
			intArrayOf(0, 0, 0)
		))
		assertCenterCellIsAliveNextBoard(startingState)
	}

	@Test
	fun `dead cell with 4 neighbors stays dead`() {
		val startingState = SimpleBoard(arrayOf(
			intArrayOf(1, 1, 1),
			intArrayOf(1, 0, 0),
			intArrayOf(0, 0, 0)
		))
		assertCenterCellIsDeadNextBoard(startingState)
	}

	@Test
	fun `dead cell with 5 neighbors stays dead`() {
		val startingState = SimpleBoard(arrayOf(
			intArrayOf(1, 1, 1),
			intArrayOf(1, 0, 1),
			intArrayOf(0, 0, 0)
		))
		assertCenterCellIsDeadNextBoard(startingState)
	}

	@Test
	fun `dead cell with 6 neighbors stays dead`() {
		val startingState = SimpleBoard(arrayOf(
			intArrayOf(1, 1, 1),
			intArrayOf(1, 0, 1),
			intArrayOf(1, 0, 0)
		))
		assertCenterCellIsDeadNextBoard(startingState)
	}

	@Test
	fun `dead cell with 7 neighbors stays dead`() {
		val startingState = SimpleBoard(arrayOf(
			intArrayOf(1, 1, 1),
			intArrayOf(1, 0, 1),
			intArrayOf(1, 1, 0)
		))
		assertCenterCellIsDeadNextBoard(startingState)
	}

	@Test
	fun `dead cell with 8 neighbors stays dead`() {
		val startingState = SimpleBoard(arrayOf(
			intArrayOf(1, 1, 1),
			intArrayOf(1, 0, 1),
			intArrayOf(1, 1, 1)
		))
		assertCenterCellIsDeadNextBoard(startingState)
	}

	private fun assertCenterCellIsDeadNextBoard(startingState: Board) {
		assertTrue(startingState.nextBoard().state[1][1] == 0)
	}

	private fun assertCenterCellIsAliveNextBoard(startingState: Board) {
		assertTrue(startingState.nextBoard().state[1][1] == 1)
	}
}
