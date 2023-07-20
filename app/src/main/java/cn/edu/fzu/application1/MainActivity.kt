package cn.edu.fzu.application1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import cn.edu.fzu.application1.adapter.RvServicesAdapter
import cn.edu.fzu.application1.adapter.RvTasksAdapter
import cn.edu.fzu.application1.databinding.ActivityMainBinding
import cn.edu.fzu.application1.entity.ItemService
import cn.edu.fzu.application1.entity.ItemTask
import cn.edu.fzu.application1.util.Util
import cn.edu.fzu.application1.util.Util.setupRecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var rvServiceAdapter: RvServicesAdapter
    private lateinit var rvTaskAdapter: RvTasksAdapter

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
    }
}