package cn.edu.fzu.application1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import cn.edu.fzu.application1.adapter.RvCardsAdapter
import cn.edu.fzu.application1.adapter.RvRecommendsAdapter
import cn.edu.fzu.application1.adapter.RvServicesAdapter
import cn.edu.fzu.application1.adapter.RvTasksAdapter
import cn.edu.fzu.application1.databinding.ActivityMainBinding
import cn.edu.fzu.application1.entity.ItemCard
import cn.edu.fzu.application1.entity.ItemRecommend
import cn.edu.fzu.application1.entity.ItemService
import cn.edu.fzu.application1.entity.ItemTask
import cn.edu.fzu.application1.util.Util.setupRecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var rvServiceAdapter: RvServicesAdapter
    private lateinit var rvTaskAdapter: RvTasksAdapter
    private lateinit var rvRecommendAdapter: RvRecommendsAdapter
    private lateinit var rvCardAdapter: RvCardsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //设置RvServices
        rvServiceAdapter= RvServicesAdapter(R.layout.item_service,mutableListOf())
        setupRecyclerView(
            binding.rvServices, //传入recyclerView对象
            rvServiceAdapter,
            listOf( //传入数据列表
                ItemService("充流量", R.drawable.ic_mobiledata, "流量告急速订购"),
                ItemService("开通自动充", R.drawable.ic_autorecharge, "专治忘充值"),
                ItemService("电子发票", R.drawable.ic_receipt, "批量开票不排队"),
                ItemService("充值记录", R.drawable.ic_bill, "可查全网记录")
            ),
            LinearLayoutManager.HORIZONTAL
        )

        //设置RvTasks
        rvTaskAdapter= RvTasksAdapter(R.layout.item_task,mutableListOf())
        setupRecyclerView(
            binding.rvTasks, //传入recyclerView对象
            rvTaskAdapter,
            listOf( //传入数据列表
                ItemTask("开启自动充值得金豆", R.drawable.ic_activaterecharge, "首次开启得","0","10金豆","去开启"),
                ItemTask("看科普视频得金豆", R.drawable.ic_video, "15s得多看多得...","0/6","5金豆","去看看")
            ),
            LinearLayoutManager.VERTICAL
        )

        //设置RvRecommends
        rvRecommendAdapter= RvRecommendsAdapter(R.layout.item_recommend,mutableListOf())
        setupRecyclerView(
            binding.rvRecommends, //传入recyclerView对象
            rvRecommendAdapter,
            listOf( //传入数据列表
                ItemRecommend("腾讯视频会员周卡", R.drawable.ic_tencentvideo, "1000金豆"),
                ItemRecommend("优酷视频会员周卡", R.drawable.ic_youku, "1500金豆"),
            ),
            LinearLayoutManager.HORIZONTAL
        )


        //设置RvCards
        rvCardAdapter= RvCardsAdapter(R.layout.item_card,mutableListOf())
        setupRecyclerView(
            binding.rvCards, //传入recyclerView对象
            rvCardAdapter,
            listOf( //传入数据列表
                ItemCard("无门槛", "翻","0","0"),
                ItemCard("无门槛", "爱奇艺","会员优惠券","去使用"),
                ItemCard("无门槛", "很遗憾","未抽中奖品","去使用"),
            ),
            LinearLayoutManager.HORIZONTAL
        )
    }
}