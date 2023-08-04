package cn.edu.fzu.application1.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import cn.edu.fzu.application1.CardActivity
import cn.edu.fzu.application1.R
import cn.edu.fzu.application1.databinding.ViewDrawPrizeBinding

class DrawPrizeView (context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs){
    //初始化绑定类
    private val binding = ViewDrawPrizeBinding.inflate(LayoutInflater.from(context), this, true)

    //创建一个ActivityResultLauncher类型的属性，用来存储注册的回调函数
    private val resultLauncher = (context as ActivityResultCaller).registerForActivityResult(ActivityResultContracts.StartActivityForResult(), object :
        ActivityResultCallback<ActivityResult> {
        override fun onActivityResult(result: ActivityResult?) {
            //根据result.resultCode和result.data来判断抽奖结果，并调用updateCardResult方法来更新UI
            if (result?.resultCode == Activity.RESULT_OK) {
                val cardResult = result.data?.getBooleanExtra("card_result", false) ?: false
                updateCardResult(cardResult)
            }
        }
    })


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

            // 使用ActivityResultLauncher对象的launch方法来启动Intent对象，并传入options.toBundle()作为参数
            resultLauncher.launch(intent, options)
        }
    }

    fun updateCardResult(result:Boolean){
        // 根据抽卡结果更新UI
        if (result) {
            // Show the card win layout in the custom view
            binding.mainCard1.setImageResource(R.drawable.ic_flip_card_win)
        } else {
            // Show the card lose layout in the custom view
            binding.mainCard1.setImageResource(R.drawable.ic_flip_card_lose)
        }

        invalidate()
    }

}