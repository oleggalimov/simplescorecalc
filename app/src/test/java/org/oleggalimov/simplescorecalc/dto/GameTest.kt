package org.oleggalimov.simplescorecalc.dto


import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GameTest {

    private val simpleTestValues = mapOf(
        "DiffGames" to Pair(
            Game("Игра1", 28, "Илья"),
            Game("Лучшая игра", 28, "Илья")
        ),
        "SameGames" to Pair(
            Game("Игра1", 28, "Илья"),
            Game("Игра1", 28, "Илья")
        ),
        "SameTitlesGames" to Pair(
            Game("Лучшая игра", 27, "Илья"),
            Game("Лучшая игра", 28, "Илья")
        ),
        "SameTitlesAndScoreGames" to Pair(
            Game("Лучшая игра", 28, "Илья"),
            Game("Лучшая игра", 28, "Петр")
        )
    )

    private val unsortedValues = arrayListOf(
        Game("Игра1", 28, "Илья"),
        Game("Game", 28, "Илья"),
        Game("Игра", 28, "Илья"),
        Game("Новая игра", 28, "Илья"),
        Game("А", 28, "Илья"),
        Game("А ", 28, "Илья"),
        Game("АААА", 28, "Илья"),
        Game("Абсолют", 28, "Илья"),
        Game("Игра1", 28, "Илья"),
        Game("Игра1", 2, "Илья"),
        Game("Игра1", 12, "Илья"),
        Game("Игра1", 28, "Петр"),
        Game("Игра1", 23, "Василий"),
        Game("", 28, "Илья"),
        Game("   ", 28, "Илья")
    )
    private val sortedValues = arrayListOf(
        Game("", 28, "Илья"),
        Game("   ", 28, "Илья"),
        Game("Game", 28, "Илья"),
        Game("А", 28, "Илья"),
        Game("А ", 28, "Илья"),
        Game("АААА", 28, "Илья"),
        Game("Абсолют", 28, "Илья"),
        Game("Игра", 28, "Илья"),
        Game("Игра1", 2, "Илья"),
        Game("Игра1", 12, "Илья"),
        Game("Игра1", 23, "Василий"),
        Game("Игра1", 28, "Илья"),
        Game("Игра1", 28, "Илья"),
        Game("Игра1", 28, "Петр"),
        Game("Новая игра", 28, "Илья")
    )

    @Test
    fun compareDiffGames() {
        val pair = simpleTestValues["DiffGames"]
        assertTrue(pair!!.first < pair.second)
    }

    @Test
    fun compareSameGames() {
        val pair = simpleTestValues["SameGames"]
        assertTrue(pair!!.first.compareTo(pair.second) == 0)
    }

    @Test
    fun compareSameTitlesGames() {
        val pair = simpleTestValues["SameTitlesGames"]
        assertTrue(pair!!.first < pair.second)
    }

    @Test
    fun compareSameTitlesAndScoreGames() {
        val pair = simpleTestValues["SameTitlesAndScoreGames"]
        assertTrue(pair!!.first < pair.second)

    }

    @Test
    fun compareSortedList() {
        unsortedValues.sort()
        assertEquals("Should be equals", unsortedValues.toString(), sortedValues.toString())
        //that's because simple assertion fails for space symbol at the end of unsortedValues
    }

}