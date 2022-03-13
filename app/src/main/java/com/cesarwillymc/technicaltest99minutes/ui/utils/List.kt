package com.cesarwillymc.technicaltest99minutes.ui.utils

import com.cesarwillymc.technicaltest99minutes.extension.ONE
import com.cesarwillymc.technicaltest99minutes.extension.ZERO

/**
 * Created by cesarwillymamanicanaza on 20/01/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */

fun List<Any>.onChangeIndexByRange(currentIndex: Int, range: Int): Int {
    if (range >= size)
        return currentIndex

    val actualSize = size - ONE
    val resultBySumValue = currentIndex + range
    return when {
        resultBySumValue < ZERO -> size + resultBySumValue
        resultBySumValue > actualSize -> resultBySumValue - size
        else -> resultBySumValue
    }
}
