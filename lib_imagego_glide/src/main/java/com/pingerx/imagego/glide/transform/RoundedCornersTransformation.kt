package com.pingerx.imagego.glide.transform

import android.graphics.*
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.pingerx.imagego.core.utils.ImageUtils
import com.pingerx.imagego.core.utils.RoundType
import java.nio.charset.Charset
import java.security.MessageDigest


/**
 * @author Pinger
 * @since 2018/7/17 下午10:22
 * 图片圆角特效，支持设置多边
 * @param radius
 * @param roundType 圆角类型
 *
 * 参考：[https://github.com/wasabeef/glide-transformations/blob/master/transformations/src/main/java/jp/wasabeef/glide/transformations/RoundedCornersTransformation.java]
 */
class RoundedCornersTransformation(private val radius: Int, private val mBorderColor: Int = 0, private val mBorderWidth: Int = 0
                                   , private val isBlackWhite: Boolean = false, private val roundType: RoundType = RoundType.ALL
) : BitmapTransformation() {

    private val diameter: Int = this.radius * 2

    private val ID: java.lang.String = java.lang.String("com.pingerx.imagego.glide.transform.RoundedCornersTransformation")
    private val ID_BYTES = ID.getBytes(Charset.forName("UTF-8"))

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        val width = toTransform.width
        val height = toTransform.height

        var bitmap = pool.get(width, height, Bitmap.Config.ARGB_8888)
        bitmap.setHasAlpha(true)

        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.shader = BitmapShader(toTransform, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        drawRoundRect(canvas, paint, width.toFloat(), height.toFloat())
        drawRoundRect(canvas, width.toFloat(), height.toFloat())
        if (isBlackWhite) {
            bitmap = ImageUtils.convertToBlackWhite(bitmap)
        }
        return bitmap
    }

    private fun drawRoundRect(canvas: Canvas, paint: Paint, width: Float, height: Float) {
        when (roundType) {
            RoundType.ALL -> canvas.drawRoundRect(RectF(0f, 0f, width, height), radius.toFloat(), radius.toFloat(), paint)
            RoundType.TOP_LEFT -> drawTopLeftRoundRect(canvas, paint, width, height)
            RoundType.TOP_RIGHT -> drawTopRightRoundRect(canvas, paint, width, height)
            RoundType.BOTTOM_LEFT -> drawBottomLeftRoundRect(canvas, paint, width, height)
            RoundType.BOTTOM_RIGHT -> drawBottomRightRoundRect(canvas, paint, width, height)
            RoundType.TOP -> drawTopRoundRect(canvas, paint, width, height)
            RoundType.BOTTOM -> drawBottomRoundRect(canvas, paint, width, height)
            RoundType.LEFT -> drawLeftRoundRect(canvas, paint, width, height)
            else -> canvas.drawRoundRect(RectF(0f, 0f, width, height), radius.toFloat(), radius.toFloat(), paint)
        }
    }

    /**
     * 绘制边框颜色
     */
    private fun drawRoundRect(canvas: Canvas, width: Float, height: Float) {
        if (mBorderWidth == 0 || mBorderColor == 0) {
            return
        }
        val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG)//设置边框样式
        borderPaint.color = mBorderColor
        borderPaint.style = Paint.Style.STROKE
        borderPaint.strokeWidth = mBorderWidth.toFloat()

        val halfBorder = (mBorderWidth / 2).toFloat()
        val path = Path()
        path.addRoundRect(RectF(halfBorder, halfBorder, width - halfBorder, height - halfBorder), radius.toFloat(), radius.toFloat(), Path.Direction.CW)
        canvas.drawPath(path, borderPaint)//绘制边框
    }

    private fun drawTopLeftRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(0f, 0f, diameter.toFloat(), diameter.toFloat()), radius.toFloat(),
                radius.toFloat(), paint)
        canvas.drawRect(RectF(0f, radius.toFloat(), radius.toFloat(), bottom), paint)
        canvas.drawRect(RectF(radius.toFloat(), 0f, right, bottom), paint)
    }

    private fun drawTopRightRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(right - diameter, 0f, right, diameter.toFloat()), radius.toFloat(),
                radius.toFloat(), paint)
        canvas.drawRect(RectF(0f, 0f, right - radius, bottom), paint)
        canvas.drawRect(RectF(right - radius, radius.toFloat(), right, bottom), paint)
    }

    private fun drawBottomLeftRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(0f, bottom - diameter, diameter.toFloat(), bottom), radius.toFloat(),
                radius.toFloat(), paint)
        canvas.drawRect(RectF(0f, 0f, diameter.toFloat(), bottom - radius), paint)
        canvas.drawRect(RectF(radius.toFloat(), 0f, right, bottom), paint)
    }

    private fun drawBottomRightRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(right - diameter, bottom - diameter, right, bottom), radius.toFloat(),
                radius.toFloat(), paint)
        canvas.drawRect(RectF(0f, 0f, right - radius, bottom), paint)
        canvas.drawRect(RectF(right - radius, 0f, right, bottom - radius), paint)
    }

    private fun drawTopRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(0f, 0f, right, diameter.toFloat()), radius.toFloat(), radius.toFloat(),
                paint)
        canvas.drawRect(RectF(0f, radius.toFloat(), right, bottom), paint)
    }

    private fun drawBottomRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(0f, bottom - diameter, right, bottom), radius.toFloat(), radius.toFloat(),
                paint)
        canvas.drawRect(RectF(0f, 0f, right, bottom - radius), paint)
    }

    private fun drawLeftRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(0f, 0f, diameter.toFloat(), bottom), radius.toFloat(), radius.toFloat(),
                paint)
        canvas.drawRect(RectF(radius.toFloat(), 0f, right, bottom), paint)
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(ID_BYTES)
    }

    override fun equals(other: Any?): Boolean {
        if (other is RoundedCornersTransformation) {
            return radius == other.radius
        }
        return false
    }

    override fun hashCode(): Int {
        return ID.hashCode()
    }
}