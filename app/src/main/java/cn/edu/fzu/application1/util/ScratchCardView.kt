package cn.edu.fzu.application1.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import cn.edu.fzu.application1.databinding.ViewDrawPrizeBinding
import cn.edu.fzu.application1.databinding.ViewScratchCardBinding

class ScratchCardView (context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs) {
    //初始化绑定类
    private val binding = ViewScratchCardBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        orientation = VERTICAL

    }

/*    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)

        val parentWidth=binding.root.measuredWidth-binding.root.paddingLeft-binding.root.paddingRight
        val scale= binding.scratchCard.measuredWidth / binding.scratchCard.measuredHeight
        //将计算好的宽度设置给ImageView
        binding.scratchCard.layoutParams.width = parentWidth
        binding.scratchCard.layoutParams.height = parentWidth / scale
        binding.scratchCard.requestLayout()

    }*/
}