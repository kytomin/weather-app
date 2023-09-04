package com.meowplex.weather_app.ui.components.astro_card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import com.meowplex.weather_app.R
import kotlin.math.pow
import kotlin.math.sqrt

@Preview
@Composable
fun SunriseView(
    @DrawableRes imageId: Int = R.drawable.sun,
    imageSide: Int = 100,
    progress: Double = 0.5,
    arcWidth: Float = 2f,
    arcColor: Color = Color.Yellow
) {

    val image = ImageBitmap.imageResource(id = imageId)

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2f)
    ) {

        val globalHeight = size.height
        val globalWidth = size.width

        val offset = computeImageOffset(
            progress = progress,
            globalHeight = globalHeight,
            globalWidth = globalWidth,
            imageSide = imageSide
        )

        val sizes = computeImageSizes(
            image = image,
            imageSide = imageSide,
            globalHeight = globalHeight,
            offset = offset
        )
        val srcSize = sizes.first
        val dstSize = sizes.second

        drawArc(
            color = arcColor,
            startAngle = 180f,
            sweepAngle = 180f,
            size = Size(globalWidth - imageSide, globalHeight * 2 - imageSide),
            style = Stroke(arcWidth, cap = StrokeCap.Square),
            topLeft = Offset(imageSide / 2f, imageSide / 2f),
            useCenter = false
        )
        drawImage(
            image = image,
            srcSize = srcSize,
            dstSize = dstSize,
            dstOffset = offset
        )
    }

}

private fun computeImageOffset(
    progress: Double,
    globalWidth: Float,
    globalHeight: Float,
    imageSide: Int
): IntOffset {
    // (x-a)^2 + (y-b)^2 = r^2
    val r = (globalWidth - imageSide) / 2
    val a = globalWidth / 2
    val b = globalHeight

    var x = progress * (globalWidth - imageSide) + imageSide * 0.5
    var y = b - sqrt(r.pow(2) - (x - a).pow(2))

    // Offset sets by topLeft
    x -= imageSide / 2
    y -= imageSide / 2
    return IntOffset(x.toInt(), y.toInt())
}

/**
 * @return [Pair] of srcSize and dstSize
 */
private fun computeImageSizes(
    image: ImageBitmap,
    offset: IntOffset,
    imageSide: Int,
    globalHeight: Float
): Pair<IntSize, IntSize> {
    var aspectRatioImage = 1f
    if (offset.y + imageSide > globalHeight) {
        val croppingHeight = offset.y + imageSide - globalHeight
        aspectRatioImage = imageSide / (imageSide - croppingHeight)
    }
    return Pair(
        IntSize(image.width, (image.width / aspectRatioImage).toInt()),
        IntSize(imageSide, (imageSide / aspectRatioImage).toInt())
    )
}



