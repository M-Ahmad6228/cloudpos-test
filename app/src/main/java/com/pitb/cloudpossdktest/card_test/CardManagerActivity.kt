package com.pitb.cloudpossdktest.card_test

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cloudpos.Device
import com.cloudpos.POSTerminal
import com.pitb.cloudpossdktest.R
import com.pitb.cloudpossdktest.databinding.ActivityCardManagerBinding
import com.pitb.cloudpossdktest.threads.ICCardThread
import com.pitb.cloudpossdktest.threads.MSRThread
import com.pitb.cloudpossdktest.threads.RFCardThread

class CardManagerActivity : AppCompatActivity() {

    private var payType = -1
    var txtSwip: TextView? = null
    var device: Device? = null
    var activity: Activity? = null
    val TAG: String = "CardReader"
    private lateinit var binding: ActivityCardManagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initParameters()
    }

    private fun initParameters() {
        activity = this@CardManagerActivity
        payType = intent.getIntExtra(CardActivity.PAY_TYPE, -1)
        Log.e(TAG, "payType = $payType")
        when (payType) {
            CardActivity.TYPE_CONTACTLESS -> {
                binding.txt0.text = getString(R.string.SwingCard)
                device = POSTerminal.getInstance(activity).getDevice("cloudpos.device.rfcardreader")
                val thread: Thread = RFCardThread(device!!, activity!!, payType)
                thread.start()
            }

            CardActivity.TYPE_MSR -> {
                binding.txt0.text = getString(R.string.SwipeCard)
                device = POSTerminal.getInstance(activity).getDevice("cloudpos.device.msr")
                val thread: Thread = MSRThread(device!!, activity!!, payType)
                thread.start()
            }

            CardActivity.TYPE_IC -> {
                binding.txt0.text = getString(R.string.InsertCard)
                device =
                    POSTerminal.getInstance(activity).getDevice("cloudpos.device.smartcardreader")
                val thread: Thread = ICCardThread(device!!, activity!!, payType)
                thread.start()
            }

            else -> {}
        }
    }
}