package cn.edu.fzu.application1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.edu.fzu.application1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //设置rvServices
        var servicesLayoutManager:LinearLayoutManager=
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvServices.layoutManager = servicesLayoutManager //用 binding.rvServices 替换 rv_service
    }
}