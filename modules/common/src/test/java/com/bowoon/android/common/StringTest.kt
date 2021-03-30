package com.bowoon.android.common

import com.bowoon.android.common.utils.commaString
import com.bowoon.android.common.utils.moneyString
import com.bowoon.android.common.utils.removeFirst
import com.bowoon.android.common.utils.removeLast
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat
import java.util.*

class StringTest {
    @Test
    fun integerNumberFormatTest() {
        val testData1 = 123
        val testData2 = 1234
        val testData3 = 12345
        val testData4 = 123456
        val testData5 = 1234567

        val numberFormat1 = NumberFormat.getNumberInstance().format(testData1)
        val numberFormat2 = NumberFormat.getNumberInstance().format(testData2)
        val numberFormat3 = NumberFormat.getNumberInstance().format(testData3)
        val numberFormat4 = NumberFormat.getNumberInstance().format(testData4)
        val numberFormat5 = NumberFormat.getNumberInstance().format(testData5)

        assertEquals(testData1.commaString, numberFormat1)
        assertEquals(testData2.commaString, numberFormat2)
        assertEquals(testData3.commaString, numberFormat3)
        assertEquals(testData4.commaString, numberFormat4)
        assertEquals(testData5.commaString, numberFormat5)
    }

    @Test
    fun stringNumberFormatTest() {
        val testData1 = "123"
        val testData2 = "1234"
        val testData3 = "12345"
        val testData4 = "123456"
        val testData5 = "1234567"

        val numberFormat1 = NumberFormat.getNumberInstance().format(testData1.toInt())
        val numberFormat2 = NumberFormat.getNumberInstance().format(testData2.toInt())
        val numberFormat3 = NumberFormat.getNumberInstance().format(testData3.toInt())
        val numberFormat4 = NumberFormat.getNumberInstance().format(testData4.toInt())
        val numberFormat5 = NumberFormat.getNumberInstance().format(testData5.toInt())

        assertEquals(testData1.commaString, numberFormat1)
        assertEquals(testData2.commaString, numberFormat2)
        assertEquals(testData3.commaString, numberFormat3)
        assertEquals(testData4.commaString, numberFormat4)
        assertEquals(testData5.commaString, numberFormat5)
    }

    @Test
    fun integerMoneyFormatTest() {
        val testData1 = 123
        val testData2 = 1234
        val testData3 = 12345
        val testData4 = 123456
        val testData5 = 1234567

        val moneyFormat1 = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(testData1)
        val moneyFormat2 = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(testData2)
        val moneyFormat3 = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(testData3)
        val moneyFormat4 = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(testData4)
        val moneyFormat5 = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(testData5)

        assertEquals(testData1.moneyString, moneyFormat1)
        assertEquals(testData2.moneyString, moneyFormat2)
        assertEquals(testData3.moneyString, moneyFormat3)
        assertEquals(testData4.moneyString, moneyFormat4)
        assertEquals(testData5.moneyString, moneyFormat5)
    }

    @Test
    fun stringMoneyFormatTest() {
        val testData1 = "123"
        val testData2 = "1234"
        val testData3 = "12345"
        val testData4 = "123456"
        val testData5 = "1234567"

        val moneyFormat1 = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(testData1.toInt())
        val moneyFormat2 = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(testData2.toInt())
        val moneyFormat3 = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(testData3.toInt())
        val moneyFormat4 = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(testData4.toInt())
        val moneyFormat5 = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(testData5.toInt())

        assertEquals(testData1.moneyString, moneyFormat1)
        assertEquals(testData2.moneyString, moneyFormat2)
        assertEquals(testData3.moneyString, moneyFormat3)
        assertEquals(testData4.moneyString, moneyFormat4)
        assertEquals(testData5.moneyString, moneyFormat5)
    }

    @Test
    fun removeLastTest() {
        var expected = "abcdefghijklmnopqrstuvwxyz"
        var actual = "abcdefghijklmnopqrstuvwxyz"

        expected.mapIndexed { _, _ ->
            assertEquals(expected.substring(0, expected.lastIndex), actual.removeLast)
            actual = actual.removeLast ?: throw IllegalArgumentException("please string argument check")
            expected = expected.substring(0, expected.lastIndex)
        }
    }

    @Test
    fun removeFirstTest() {
        var expected = "abcdefghijklmnopqrstuvwxyz"
        var actual = "abcdefghijklmnopqrstuvwxyz"

        expected.mapIndexed { _, _ ->
            assertEquals(expected.substring(1, expected.length), actual.removeFirst)
            actual = actual.removeFirst ?: throw IllegalArgumentException("please string argument check")
            expected = expected.substring(1, expected.length)
        }
    }
}