package cn.edu.fzu.application1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //设置服务recyclerview
        var rv_services:RecyclerView=findViewById(R.id.rv_services)
        var servicesLayoutManager:LinearLayoutManager=
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_services.layoutManager = servicesLayoutManager


    }
}