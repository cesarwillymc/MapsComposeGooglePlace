package com.cesarwillymc.technicaltest99minutes.ui.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import coil.compose.rememberImagePainter
import com.cesarwillymc.technicaltest99minutes.extension.ANGLE180
import com.cesarwillymc.technicaltest99minutes.extension.DELAY_ML_MIN
import com.cesarwillymc.technicaltest99minutes.extension.LIMIT_OFFSET
import com.cesarwillymc.technicaltest99minutes.extension.LIMIT_OFFSET_NEGATIVE
import com.cesarwillymc.technicaltest99minutes.extension.LIMIT_OFFSET_Y
import com.cesarwillymc.technicaltest99minutes.extension.LIMIT_OFFSET_Y_NEGATIVE
import com.cesarwillymc.technicaltest99minutes.extension.LIMIT_SCALE_MAX
import com.cesarwillymc.technicaltest99minutes.extension.LIMIT_SCALE_MIN
import com.cesarwillymc.technicaltest99minutes.extension.MAX_ZOOM_IMAGE
import com.cesarwillymc.technicaltest99minutes.extension.ONE_FLOAT
import com.cesarwillymc.technicaltest99minutes.extension.TWO
import com.cesarwillymc.technicaltest99minutes.extension.ZERO
import com.cesarwillymc.technicaltest99minutes.extension.ZEROF
import com.cesarwillymc.technicaltest99minutes.ui.theme.White
import com.cesarwillymc.technicaltest99minutes.ui.utils.getLinkPhotoGoogle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

/**
 * Created by Willy on 11/17/2021.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 *
 * Android Jetpack Compose: how to zoom a image in a "box"?
 *
 * https://stackoverflow.com/a/67234570
 */
@ExperimentalComposeUiApi
@SuppressWarnings("unused", "LongMethod")
@Composable
fun ZoomImage(
    imageUrl: String,
    height: Dp,
    currentIndexImg: Int,
    onSwipeLeft: () -> Unit,
    onSwipeRight: () -> Unit
) {
    var currentIndex by remember {
        mutableStateOf(ZERO)
    }

    var scale by remember {
        mutableStateOf(ONE_FLOAT)
    }
    var offsetX by remember { mutableStateOf(ZEROF) }
    var offsetY by remember { mutableStateOf(ZEROF) }
    var swipeX by remember { mutableStateOf(ZEROF) }

    var isValidSwipe by remember {
        mutableStateOf(false)
    }

    val isSameIndexImage = currentIndex != currentIndexImg

    /** Re init params when i change the index image**/
    if (isSameIndexImage) {
        offsetX = ZEROF
        offsetY = ZEROF
        scale = ONE_FLOAT
        currentIndex = currentIndexImg
    }
    /** Only detect when I do swipe  without Zoom**/
    LaunchedEffect(swipeX) {
        snapshotFlow {
            swipeX
        }.collectLatest {
            delay(DELAY_ML_MIN)
            if (isValidSwipe) {
                when {
                    swipeX > ZERO -> onSwipeRight()
                    swipeX < ZERO -> onSwipeLeft()
                }
            }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RectangleShape)
            .background(White)
            .pointerInteropFilter { motionEvent ->
                /** Detect how many fingers are on the screen **/
                isValidSwipe = motionEvent.pointerCount != TWO
                true
            }
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, gestureRotate ->
                    val (xPan, _) = pan
                    val actualValueScale = scale * zoom
                    if (actualValueScale >= ONE_FLOAT && actualValueScale < MAX_ZOOM_IMAGE)
                        scale = actualValueScale
                    else if (actualValueScale < ONE_FLOAT) {
                        offsetX = ZEROF
                        offsetY = ZEROF
                        scale = ONE_FLOAT
                    }
                    /** Enter if there is a zoom and move the image  **/
                    if (scale > ONE_FLOAT) {
                        val x = pan.x * zoom
                        val y = pan.y * zoom
                        val angleRad = gestureRotate * PI / ANGLE180

                        val offsetXTemp =
                            offsetX + (x * cos(angleRad) - y * sin(angleRad)).toFloat()
                        val offsetYTemp =
                            offsetY + (x * sin(angleRad) + y * cos(angleRad)).toFloat()

                        if (offsetXTemp > LIMIT_OFFSET_NEGATIVE && offsetXTemp < LIMIT_OFFSET) {
                            offsetX = offsetXTemp
                        }
                        if (offsetYTemp > LIMIT_OFFSET_Y_NEGATIVE && offsetYTemp < LIMIT_OFFSET_Y) {
                            offsetY = offsetYTemp
                        }
                    } else {
                        swipeX = xPan
                    }
                }
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(
            painter = rememberImagePainter(imageUrl.getLinkPhotoGoogle(), builder = {
                crossfade(true)
            }),
            contentDescription = null,
            modifier = Modifier
                .height(height)
                .fillMaxWidth()
                .offset {
                    IntOffset(offsetX.roundToInt(), offsetY.roundToInt())
                }
                .align(Alignment.CenterVertically)
                .graphicsLayer {
                    scaleX = maxOf(LIMIT_SCALE_MIN, minOf(LIMIT_SCALE_MAX, scale))
                    scaleY = maxOf(LIMIT_SCALE_MIN, minOf(LIMIT_SCALE_MAX, scale))
                }
        )
    }
}
