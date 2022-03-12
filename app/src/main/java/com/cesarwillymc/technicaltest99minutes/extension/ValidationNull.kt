package com.cesarwillymc.technicaltest99minutes.extension

/**
 * Created by Willy on 11/16/2021.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
fun Int?.orEmpty(): Int = this ?: 0

fun Boolean?.orEmpty(): Boolean = this ?: false

fun Double?.orEmpty(): Double = this ?: 0.0

fun Float?.orEmpty(): Float = this ?: 0F

inline fun <reified T> List<T>?.orEmpty(): List<T> = this ?: listOf<T>()
