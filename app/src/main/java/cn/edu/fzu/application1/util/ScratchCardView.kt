package cn.edu.fzu.application1.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import cn.edu.fzu.application1.R
import cn.edu.fzu.application1.adapter.ViewPagerAdapter
import cn.edu.fzu.application1.databinding.ViewScratchCardBinding
import cn.edu.fzu.application1.fragment.ScratchCardFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.random.Random

class ScratchCardView (context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs) {
    //初始化绑定类
    private val binding = ViewScratchCardBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        orientation = VERTICAL

        val pagerAdapter= ViewPagerAdapter(context as FragmentActivity)
        // 给adapter添加四个fragment对象，可以自定义fragment的内容和布局
        pagerAdapter.addFragment(ScratchCardFragment())
        pagerAdapter.addFragment(Fragment())
        
        binding.scratchViewPager.adapter=pagerAdapter

        // 使用TabLayoutMediator来关联TabLayout和ViewPager2，传入tabLayout, viewPager和一个回调函数
        TabLayoutMediator(binding.scratchTabLayout, binding.scratchViewPager) { tab, position ->
            // 在回调函数中，给每个tab设置标题
            // 定义一个数组，存放四个fragment的标题
            val titles = arrayOf("1", "2")
            tab.text = titles[position]
        }.attach() // 调用attach方法来完成关联

        if(isWin()){
            binding.scratchCard.setSrcResult(R.drawable.pic_scratch_win)
        }else{
            binding.scratchCard.setSrcResult(R.drawable.pic_scratch_lose)
        }
    }

    //根据随机数的值来判断是否中奖
    fun isWin(): Boolean {
        // 生成一个随机的布尔值
        val bool = Random.nextBoolean()
        // 根据布尔值返回0或1
        return bool
    }
}