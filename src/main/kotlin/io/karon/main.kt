package io.karon

fun main() {
	val patternLoader = PatternLoader("/patterns")
	val startingState = patternLoader.glider
	var board: Board = SimpleBoard(startingState.size, startingState[0].size) { i, j -> startingState[i][j] }
	board.state.prettyPrint()

	while (true) {
		Thread.sleep(500)
		board = board.nextBoard()
		board.state.prettyPrint()
	}
}

fun Array<IntArray>.prettyPrint() {
	val sideBorder = "|"
	val topAndBotBorders = "-".repeat(this[0].size + sideBorder.length * 2)

	val result = StringBuilder().appendLine(topAndBotBorders)
	this.forEach {
		result.append(sideBorder)
			.append(it.render())
			.appendLine(sideBorder)
	}
	result.appendLine(topAndBotBorders)
	println(result.toString())
}

fun IntArray.render() = this.fold("") { acc, cell -> acc + cell.render() }

fun Int.render() = if (this == 0) " " else "#"
