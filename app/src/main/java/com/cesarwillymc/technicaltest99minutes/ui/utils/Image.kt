package com.cesarwillymc.technicaltest99minutes.ui.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import com.cesarwillymc.technicaltest99minutes.BuildConfig
import com.cesarwillymc.technicaltest99minutes.extension.SIZE_400
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

/**
 * Created by Willy on 11/24/2021.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
fun Int.bitmapDescriptorFromVector(context: Context): BitmapDescriptor? {
    return ContextCompat.getDrawable(context, this)?.run {
        setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
        draw(Canvas(bitmap))
        BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}

fun String.getLinkPhotoGoogle(size: Int = SIZE_400): String {
    return "${BuildConfig.MAPS_URL}place/photo?maxwidth=$size&photo_reference=$this&key=${BuildConfig.MAPS_API_KEY}"
}
