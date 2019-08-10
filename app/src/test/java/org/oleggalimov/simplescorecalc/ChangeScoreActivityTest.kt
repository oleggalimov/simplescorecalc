package org.oleggalimov.simplescorecalc

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.BeforeClass
import org.oleggalimov.simplescorecalc.activities.ChangeScoreActivity
import org.oleggalimov.simplescorecalc.enums.OperationType
import java.lang.Exception
import java.lang.NullPointerException
import java.lang.UnsupportedOperationException


class ChangeScoreActivityTest {
    @Test (expected = NumberFormatException::class)
    fun formatException() {
        ChangeScoreActivity().changeScore(0,"asdfa", OperationType.REMOVE_POINTS)
    }
    @Test (expected = UnsupportedOperationException::class)
    fun throwsUnsupportedOperationExOnMaxValue() {
        ChangeScoreActivity().changeScore(1,Int.MAX_VALUE.toString(), OperationType.ADD_POINTS)
    }
    @Test (expected = UnsupportedOperationException::class)
    fun throwsUnsupportedOperationExOnMinValue() {
        ChangeScoreActivity().changeScore(-2,Int.MAX_VALUE.toString(), OperationType.REMOVE_POINTS)
    }
}