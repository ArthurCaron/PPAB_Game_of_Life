package io.karon

import java.io.File

class PatternLoader(patternFolder: String) {
	private val resourceFolder = PatternLoader::class.java.getResource(patternFolder)?.file

	// oscillators
	val beacon by lazy { readToArray(File("$resourceFolder/oscillators/beacon.txt")) }
	val blinker by lazy { readToArray(File("$resourceFolder/oscillators/blinker.txt")) }
	val pentaDecathlon by lazy { readToArray(File("$resourceFolder/oscillators/penta_decathlon.txt")) }
	val pulsar by lazy { readToArray(File("$resourceFolder/oscillators/pulsar.txt")) }
	val toad by lazy { readToArray(File("$resourceFolder/oscillators/toad.txt")) }

	// spaceships
	val glider by lazy { readToArray(File("$resourceFolder/spaceships/glider.txt")) }
	val gosperGliderGun by lazy { readToArray(File("$resourceFolder/spaceships/gosper_glider_gun.txt")) }
	val heavyweightSpaceship by lazy { readToArray(File("$resourceFolder/spaceships/heavyweight_spaceship.txt")) }
	val lightweightSpaceship by lazy { readToArray(File("$resourceFolder/spaceships/lightweight_spaceship.txt")) }
	val middleweightSpaceship by lazy { readToArray(File("$resourceFolder/spaceships/middleweight_spaceship.txt")) }

	// still lifes (it's how it's written in wikipedia :D)
	val beehive by lazy { readToArray(File("$resourceFolder/still_lifes/beehive.txt")) }
	val block by lazy { readToArray(File("$resourceFolder/still_lifes/block.txt")) }
	val boat by lazy { readToArray(File("$resourceFolder/still_lifes/boat.txt")) }
	val loaf by lazy { readToArray(File("$resourceFolder/still_lifes/loaf.txt")) }
	val tub by lazy { readToArray(File("$resourceFolder/still_lifes/tub.txt")) }

	private fun readToArray(file: File): Array<IntArray> {
		val content = file.readLines()
		val height = content.size

		val biggestLine = content.map { it.count() }
			.toSet()
			.maxOrNull() ?: 0

		return Array(height) { lineIndex ->
			val map = content[lineIndex]
				.toCharArray()
				.map { Character.getNumericValue(it) }
			IntArray(biggestLine) { cellIndex ->
				if (cellIndex < map.size) map[cellIndex] else 0
			}
		}
	}
}
