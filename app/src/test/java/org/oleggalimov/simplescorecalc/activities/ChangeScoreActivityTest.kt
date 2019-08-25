package org.oleggalimov.simplescorecalc.activities

import org.junit.Test
import org.oleggalimov.simplescorecalc.enums.OperationType


class ChangeScoreActivityTest {
    @Test(expected = NumberFormatException::class)
    fun formatException() {
        ChangeScoreActivity().changeScore(0, "asdfa", OperationType.REMOVE_POINTS)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun throwsUnsupportedOperationExOnMaxValue() {
        ChangeScoreActivity().changeScore(1, Int.MAX_VALUE.toString(), OperationType.ADD_POINTS)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun throwsUnsupportedOperationExOnMinValue() {
        ChangeScoreActivity().changeScore(-2, Int.MAX_VALUE.toString(), OperationType.REMOVE_POINTS)
    }
}