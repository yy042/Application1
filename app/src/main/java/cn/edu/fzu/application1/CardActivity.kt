package cn.edu.fzu.application1

import android.animation.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import cn.edu.fzu.application1.databinding.ActivityCardBinding
import cn.edu.fzu.application1.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import com.gyf.immersionbar.ImmersionBar

class CardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        val binding = ActivityCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //设置沉浸式
        ImmersionBar.with(this)
            .init()


        // 获取卡片视图和光束视图的引用
        val card = binding.card
        val ray = binding.cardRay
        val popup=binding.popup
        val cheer=binding.ivCheer

        // 创建一个动画集合，用于存放所有的动画
        val animationSet = AnimatorSet()

        // 卡片缩放由100%放大到150%（缩放时间：0.2s）
        val scaleCard1 = ObjectAnimator.ofPropertyValuesHolder(card,
        PropertyValuesHolder.ofFloat("scaleX", 1f, 1.5f),
        PropertyValuesHolder.ofFloat("scaleY", 1f, 1.5f)).apply {
            duration = 200  // 设置动画时间为0.2秒
        }

        // 创建第二步的动画
        // 卡片旋转由0度转向+15度（旋转时间0.2s）
        val rotateCard1 = ObjectAnimator.ofFloat(card, "rotation", 0f, 15f).apply {
            duration = 200  // 设置动画时间为0.2秒
        }

        // 光束出场从0%缩放至60%（缩放时间0.2s）
        val scaleRay1 = ObjectAnimator.ofPropertyValuesHolder(ray,
            PropertyValuesHolder.ofFloat("scaleX", 0f, 0.6f),
            PropertyValuesHolder.ofFloat("scaleY", 0f, 0.6f)
        ).apply {
            duration = 200  // 设置动画时间为0.2秒
        }


        // 创建第三步的动画
        // 卡片旋转由+15度转向0度（旋转时间0.2s）
        val rotateCard2 = ObjectAnimator.ofFloat(card, "rotation", 15f, 0f).apply {
            duration = 200  // 设置动画时间为0.2秒
        }

        // 光束从60%缩放至50%（缩放时间0.2s）
        val scaleRay2 = ObjectAnimator.ofPropertyValuesHolder(ray,
            PropertyValuesHolder.ofFloat("scaleX", 0.6f, 0.5f),
            PropertyValuesHolder.ofFloat("scaleY", 0.6f, 0.5f)
        ).apply {
            duration = 200  // 设置动画时间为0.2秒
        }

        // 创建第四步的动画
        // 卡片旋转由0度转向-15度（旋转时间0.2s）
        val rotateCard3 = ObjectAnimator.ofFloat(card, "rotation", 0f, -15f).apply {
            duration = 200  // 设置动画时间为0.2秒
        }

        // 光束从50%缩放至60%（缩放时间0.2s）
        val scaleRay3 = ObjectAnimator.ofPropertyValuesHolder(ray,
            PropertyValuesHolder.ofFloat("scaleX", 0.5f, 0.6f),
            PropertyValuesHolder.ofFloat("scaleY", 0.5f, 0.6f)
        ).apply {
            duration = 200  // 设置动画时间为0.2秒
        }



        // 创建第五步的动画
        // 卡片旋转由-15度转向0度（旋转时间0.2s）
        val rotateCard4 = ObjectAnimator.ofFloat(card, "rotation", -15f, 0f).apply {
            duration = 200  // 设置动画时间为0.2秒
        }

        // 光束从60%缩放至50%（缩放时间0.2s）
        val scaleRay4 = ObjectAnimator.ofPropertyValuesHolder(ray,
            PropertyValuesHolder.ofFloat("scaleX", 0.6f, 0.5f),
            PropertyValuesHolder.ofFloat("scaleY", 0.6f, 0.5f)
        ).apply {
            duration = 200  // 设置动画时间为0.2秒
        }

        // 创建第六步的动画
        // 光束从50%缩放至60%（缩放时间0.2s）
        val scaleRay5 = ObjectAnimator.ofPropertyValuesHolder(ray,
            PropertyValuesHolder.ofFloat("scaleX", 0.5f, 0.6f),
            PropertyValuesHolder.ofFloat("scaleY", 0.5f, 0.6f)
        ).apply {
            duration = 200  // 设置动画时间为0.2秒
        }

        // 创建第七步的动画
        // 卡片绕y轴旋转由0度旋转至270度（旋转时间0.4s）
        // 卡片绕y轴旋转由0度旋转至360度（旋转时间0.4s）
        val rotateCard5 = ObjectAnimator.ofFloat(card, "rotationY", 0f, -270f).apply {
            duration = 400 // 设置动画时间为0.4秒
        }


        // 卡片缩放由150%放大至300%（缩放时间0.4s）
        val scaleCard2 = ObjectAnimator.ofPropertyValuesHolder(card,
            PropertyValuesHolder.ofFloat("scaleX", 1.5f, 3f),
            PropertyValuesHolder.ofFloat("scaleY", 1.5f, 3f)
        ).apply {
            duration = 400  // 设置动画时间为0.4秒
        }


        // 光束消失透明度由100%变至0%（变化时间0.2s）
        val fadeRay1 = ObjectAnimator.ofFloat(ray, "alpha", 1f, 0f).apply {
            duration = 200  // 设置动画时间为0.2秒
        }

        // 弹窗绕y轴翻转由270度转至360度（旋转时间0.2s）
        val rotatePopup1 = ObjectAnimator.ofFloat(popup, "rotationY", -270f, -360f).apply {
            duration = 200 // 设置动画时间为0.2秒
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {
                    // 动画开始时
                    popup.visibility = View.VISIBLE
                }

                override fun onAnimationEnd(animation: Animator?) {
                    // 动画结束时
                    Glide.with(this@CardActivity).load(R.drawable.gif_cheer).into(cheer)
                    cheer.visibility=View.VISIBLE

                }

                override fun onAnimationCancel(animation: Animator?) {
                    // 动画取消时，不做任何操作
                }

                override fun onAnimationRepeat(animation: Animator?) {
                    // 动画重复时，不做任何操作
                }
            })
        }

        // 光束出现透明度由0%变至100%（变化时间0.2s）
        val fadeRay2 = ObjectAnimator.ofFloat(ray, "alpha", 0f, 1f).apply {
            duration = 200 // 设置动画时间为0.2秒
        }

        // 光束从60%缩放至100%（缩放时间0.2s）
        val scaleRay6 = ObjectAnimator.ofPropertyValuesHolder(ray,
            PropertyValuesHolder.ofFloat("scaleX", 0.6f, 1f),
            PropertyValuesHolder.ofFloat("scaleY", 0.6f, 1f)
        ).apply {
            duration = 200 // 设置动画时间为0.2秒
        }

        // 创建第九步的动画
        // 光束以4s一圈的速度进行旋转
        val rotateRay1 = ObjectAnimator.ofFloat(ray, "rotation", 0f, 360f).apply {
            duration = 4000 // 设置动画时间为4秒
            repeatCount = ValueAnimator.INFINITE // 设置重复次数为无限
            interpolator = LinearInterpolator()
        }

        // 将所有的动画添加到动画集合中，并设置播放顺序
        animationSet.play(scaleCard1) // 播放第一步
        animationSet.play(rotateCard1).with(scaleRay1).after(scaleCard1)  // 第二步同时播放
        animationSet.play(rotateCard2).with(scaleRay2).after(rotateCard1)  // 第三步在第二步之后播放
        animationSet.play(rotateCard3).with(scaleRay3).after(rotateCard2)  // 第四步在第三步之后播放
        animationSet.play(rotateCard4).with(scaleRay4).after(rotateCard3)  // 第五步在第四步之后播放
        animationSet.play(scaleRay5).after(rotateCard4)  // 第六步在第五步之后播放
        animationSet.play(rotateCard5).with(scaleCard2).with(fadeRay1).after(scaleRay5)  // 第七步在第六步之后播放
        animationSet.play(rotatePopup1).with(fadeRay2).with(scaleRay6).after(rotateCard5) // 第七步中的弹窗翻转、光束出现和光束缩放在卡片旋转之后同时播放
        animationSet.play(rotateRay1).after(rotatePopup1) // 第九步中的光束旋转在弹窗翻转之后播放
         // 启动动画集合
        animationSet.start()

    }
}