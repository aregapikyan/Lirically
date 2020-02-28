package com.example.myapplication.animation

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView


fun scaleView(v1: View, startScale: Float, endScale: Float, isChecked: Boolean) {
    val anim = ScaleAnimation(
        startScale, endScale, // Start and end values for the X axis scaling
        startScale, endScale, // Start and end values for the Y axis scaling
        Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
        Animation.RELATIVE_TO_SELF, 0.5f
    ) // Pivot point of Y scaling

    anim.repeatMode = ScaleAnimation.REVERSE
    anim.fillAfter = true // Needed to keep the result of the animation
    anim.duration = 150

    val animReverse = ScaleAnimation(
        endScale, startScale, // Start and end values for the X axis scaling
        endScale, startScale, // Start and end values for the Y axis scaling
        Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
        Animation.RELATIVE_TO_SELF, 0.5f
    )
    animReverse.fillAfter = true
    animReverse.duration = 150
    animReverse.startTime = 500

    v1.startAnimation(anim)

    anim.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {

        }

        override fun onAnimationEnd(animation: Animation?) {
            v1.startAnimation(animReverse)
        }

        override fun onAnimationStart(animation: Animation?) {
            if (isChecked) {
                dynamicColorChange(v1 as ImageView, Color.TRANSPARENT, Color.RED, 150, 0, 0, false)
            } else {
                dynamicColorChange(v1 as ImageView, Color.RED, Color.TRANSPARENT,150, 0, 0, false)
            }
        }

    })

}

fun dynamicColorChange(
    view: ImageView,
    colorFrom: Int,
    colorTo: Int,
    startDuration: Long,
    endDuration: Long,
    endStartDelay: Long,
    reverse: Boolean
) {
    val colorAnimationStart: ValueAnimator =
        ValueAnimator.ofObject(object : ArgbEvaluator() {}, colorFrom, colorTo)
    colorAnimationStart.duration = startDuration
    val colorAnimationEnd: ValueAnimator =
        ValueAnimator.ofObject(object : ArgbEvaluator() {}, colorTo, colorFrom)
    colorAnimationEnd.duration = endDuration

    colorAnimationStart.addUpdateListener { animation ->
        view.setColorFilter(
            animation?.animatedValue as Int,
            android.graphics.PorterDuff.Mode.SRC_ATOP
        )
    }

    colorAnimationEnd.startDelay = endStartDelay
    colorAnimationStart.start()

    colorAnimationStart.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {

        }

        override fun onAnimationEnd(animation: Animator?) {
            if (reverse) {
                colorAnimationEnd.addUpdateListener { animation ->
                    view.setColorFilter(
                        animation?.animatedValue as Int,
                        android.graphics.PorterDuff.Mode.SRC_ATOP
                    )
                }

                colorAnimationEnd.start()
            }
        }

        override fun onAnimationCancel(animation: Animator?) {
        }

        override fun onAnimationStart(animation: Animator?) {
        }

    })


}