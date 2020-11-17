package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.ManageUtil.ServiceManage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MyActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt_hello.setOnClickListener {

            var resTechnical = ServiceManage(applicationContext).getTechnicalOrderMaster()
            CustomDialog(resTechnical).show(supportFragmentManager, "Dialog")
           // var resSystem = ServiceManage(applicationContext).getSystemMaster()
         //   CustomDialog(resSystem).show(supportFragmentManager, "Dialog")
//            var resAircraft = ServiceManage(applicationContext).getAircraftMaster()
//            CustomDialog(resAircraft).show(supportFragmentManager, "Dialog")

        }
    }


}