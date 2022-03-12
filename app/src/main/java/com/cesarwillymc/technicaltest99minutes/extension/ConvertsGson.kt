package com.cesarwillymc.technicaltest99minutes.extension

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * Created by Willy on 11/15/2021.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */

inline fun <reified T> toJson(model: T): String {
    return Gson().toJson(model)
}

inline fun <reified T> fromJson(model: String): T {
    val type: Type = object : TypeToken<T>() {}.type
    return Gson().fromJson(model, type) as T
}
