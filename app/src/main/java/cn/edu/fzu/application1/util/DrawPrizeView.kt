package cn.edu.fzu.application1.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import cn.edu.fzu.application1.CardActivity
import cn.edu.fzu.application1.databinding.ViewDrawPrizeBinding

class DrawPrizeView (context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs){
    //初始化绑定类
    private val binding = ViewDrawPrizeBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        orientation=VERTICAL

        binding.mainCard1.setOnClickListener{
            // 创建一个Intent对象，指定要跳转到的Activity类
            val intent = Intent(context, CardActivity::class.java)

            // 创建一个 ActivityOptionsCompat 对象
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context as Activity, // 需要将context强制转换为Activity类型
                binding.mainCard1,
                ViewCompat.getTransitionName(binding.mainCard1) ?: "card" // 使用ViewCompat类来获取视图的transitionName，如果为空则使用默认值
            )

            // 使用context对象和options对象来启动这个Intent对象
            context.startActivity(intent, options.toBundle())
        }
    }
}