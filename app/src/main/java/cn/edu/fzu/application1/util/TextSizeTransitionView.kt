package cn.edu.fzu.application1.util

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

//创建一个自定义的View类，继承自TextView，并重写onDraw方法来实现字号的缩放效果和位移
class TextSizeTransitionView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    // 用于保存原始的文字大小
    private var originalTextSize = 0f

    init {
        // 获取原始的文字大小
        originalTextSize = textSize
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // 调用父类的onMeasure方法，获取测量后的宽高
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // 如果原始的文字大小不为0，且当前的文字大小和原始的不一致，则说明发生了动画变化
        if (originalTextSize != 0f && textSize != originalTextSize) {
            // 根据当前的文字大小和原始的文字大小计算一个缩放比例
            val scale = textSize / originalTextSize

            // 根据缩放比例调整测量后的宽高
            val newWidth = (measuredWidth * scale).toInt()
            val newHeight = (measuredHeight * scale).toInt()

            // 重新设置测量后的宽高
            setMeasuredDimension(newWidth, newHeight)
        }
    }
}
