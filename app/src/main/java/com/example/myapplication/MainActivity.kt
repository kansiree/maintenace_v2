package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Database.AppDatabase
import com.example.myapplication.Database.DatabaseManage
import com.example.myapplication.Database.Entity.TechnicalOrderEntity
import com.example.myapplication.ManageService.OnResponseServiceCallBack
import com.example.myapplication.ManageService.ServiceManage
import com.example.myapplication.Response.MasterResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val TAG = "MyActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt_hello.setOnClickListener {
            Log.i(TAG,"click")

            ServiceManage(applicationContext).getTechnicalOrderMaster(object :OnResponseServiceCallBack{
                override fun onSuccess(model: Any?) {

                    Log.i(TAG,model.toString())
                    var responses: MasterResponse = model as MasterResponse
                    var resInsertDatabase = DatabaseManage(applicationContext).insertTechnicalOrder(responses)
                    CustomDialog(resInsertDatabase).show(supportFragmentManager, "Dialog")

                }

                override fun onFailure(message: String?, t: Throwable?) {
                    TODO("Not yet implemented")
                }

                override fun onServiceFailure(returnCode: String, returnMessage: String) {
                    TODO("Not yet implemented")
                }

            })
          //

           // var resSystem = ServiceManage(applicationContext).getSystemMaster()
         //   CustomDialog(resSystem).show(supportFragmentManager, "Dialog")
//            var resAircraft = ServiceManage(applicationContext).getAircraftMaster()
//            CustomDialog(resAircraft).show(supportFragmentManager, "Dialog")

        }
    }


}