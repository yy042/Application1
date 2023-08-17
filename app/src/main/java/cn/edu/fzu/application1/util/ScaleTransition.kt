package cn.edu.fzu.application1.util

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.transition.ChangeBounds
import android.transition.TransitionValues
import android.view.View
import android.view.ViewGroup

class ScaleTransition : ChangeBounds() {

    private val PROPNAME_SCALE_X = "scaleTransition:scaleX"
    private val PROPNAME_SCALE_Y = "scaleTransition:scaleY"

    override fun captureStartValues(transitionValues: TransitionValues) {
        super.captureStartValues(transitionValues)
        val view = transitionValues.view
        transitionValues.values[PROPNAME_SCALE_X] = view.scaleX
        transitionValues.values[PROPNAME_SCALE_Y] = view.scaleY
    }

    override fun captureEndValues(transitionValues: TransitionValues) {
        super.captureEndValues(transitionValues)
        val view = transitionValues.view
        transitionValues.values[PROPNAME_SCALE_X] = view.scaleX
        transitionValues.values[PROPNAME_SCALE_Y] = view.scaleY
    }

    override fun createAnimator(
        sceneRoot: ViewGroup,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator? {
        // Get the return value of super.createAnimator method
        var animator = super.createAnimator(sceneRoot, startValues, endValues)
        if (startValues == null || endValues == null || animator == null) {
            return null
        }
        val view = endValues.view
        val startScaleX = startValues.values[PROPNAME_SCALE_X] as Float
        val startScaleY = startValues.values[PROPNAME_SCALE_Y] as Float
        val endScaleX = endValues.values[PROPNAME_SCALE_X] as Float
        val endScaleY = endValues.values[PROPNAME_SCALE_Y] as Float

        if (startScaleX != endScaleX || startScaleY != endScaleY) {
            // Create a scale animation that scales the child views along with the parent view
            val childCount = (view as ViewGroup).childCount
            for (i in 0 until childCount) {
                val child = view.getChildAt(i)
                val childStartScaleX = child.scaleX
                val childStartScaleY = child.scaleY
                val childEndScaleX = childStartScaleX * endScaleX / startScaleX
                val childEndScaleY = childStartScaleY * endScaleY / startScaleY
                val scaleX = ObjectAnimator.ofFloat(child, View.SCALE_X, childStartScaleX, childEndScaleX)
                val scaleY = ObjectAnimator.ofFloat(child, View.SCALE_Y, childStartScaleY, childEndScaleY)
                // Check if the animator is an instance of AnimatorSet
                if (animator is AnimatorSet) {
                    // Use the playTogether method of animator to add the scale animations
                    animator.playTogether(scaleX, scaleY)
                } else {
                    // Create a new AnimatorSet object and use the play method to play the animator and the scale animations together
                    val animatorSet = AnimatorSet()
                    animatorSet.play(animator).with(scaleX).with(scaleY)
                    // Assign the animatorSet to the animator variable
                    animator = animatorSet
                }
            }
            // Create a scale animation that restores the parent view's scale values
            val scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, startScaleX, endScaleX)
            val scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, startScaleY, endScaleY)
            // Check if the animator is an instance of AnimatorSet
            if (animator is AnimatorSet) {
                // Use the playTogether method of animator to add the scale animations
                animator.playTogether(scaleX, scaleY)
            } else {
                // Create a new AnimatorSet object and use the play method to play the animator and the scale animations together
                val animatorSet = AnimatorSet()
                animatorSet.play(animator).with(scaleX).with(scaleY)
                // Assign the animatorSet to the animator variable
                animator = animatorSet
            }
        }
        return animator
    }

}
