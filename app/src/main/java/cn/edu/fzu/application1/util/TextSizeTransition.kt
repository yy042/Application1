package cn.edu.fzu.application1.util

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.transition.Transition
import android.transition.TransitionValues
import android.util.Log
import android.util.TypedValue
import android.view.ViewGroup

//创建一个自定义的Transition类，继承自Transition，并重写captureStartValues和captureEndValues方法来捕获目标视图在转场前后的字号大小和位置，以及createAnimator方法来创建动画效果
class TextSizeTransition : Transition() {

    companion object {
        // 定义一个属性名，用于保存文字大小信息
        private const val PROPNAME_TEXT_SIZE = "textSizeTransition:textSize"
    }

    override fun captureStartValues(transitionValues: TransitionValues) {
        // 获取开始状态下的View对象
        val view = transitionValues.view

        // 如果是TextSizeTransitionView类型，则获取其文字大小，并保存到values集合中
        if (view is TextSizeTransitionView) {
            val textSize = view.textSize
            transitionValues.values[PROPNAME_TEXT_SIZE] = textSize
        }
    }

    override fun captureEndValues(transitionValues: TransitionValues) {
        // 获取结束状态下的View对象
        val view = transitionValues.view

        // 如果是TextSizeTransitionView类型，则获取其文字大小，并保存到values集合中
        if (view is TextSizeTransitionView) {
            val textSize = view.textSize
            transitionValues.values[PROPNAME_TEXT_SIZE] = textSize
        }
    }

    override fun createAnimator(
        sceneRoot: ViewGroup,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator? {
        // 如果开始或结束的值为空，则返回null，表示不执行动画
        if (startValues == null || endValues == null) {
            return null
        }

        // 获取开始和结束状态下的View对象
        val startView = startValues.view as TextSizeTransitionView
        val endView = endValues.view as TextSizeTransitionView

        // 获取开始和结束状态下的文字大小
        val startSize = startValues.values[PROPNAME_TEXT_SIZE] as Float
        val endSize = endValues.values[PROPNAME_TEXT_SIZE] as Float

        // 如果开始和结束状态下的文字大小不一致，则创建一个属性动画，用于改变文字大小
        if (startSize != endSize) {
            val animator = ObjectAnimator.ofFloat(startView, "textSize", startSize, endSize)
            // 设置一个更新监听器，用于在动画过程中同步更新结束状态下的View的文字大小
            animator.addUpdateListener { animation ->
                val value = animation.animatedValue as Float
                endView.setTextSize(TypedValue.COMPLEX_UNIT_PX, value)
                Log.i("TAG","TextSize is $value")
            }
            return animator
        }

        // 否则返回null，表示不执行动画
        return null
    }
}
