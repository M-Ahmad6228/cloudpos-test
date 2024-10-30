package com.pitb.cloudpossdktest.card_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pitb.cloudpossdktest.databinding.ActivityInputPinactivityBinding
import com.pitb.cloudpossdktest.threads.WaitForPINThread

class InputPINActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputPinactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputPinactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initParameters()
    }

    private fun initParameters() {
        val thread = WaitForPINThread(this)
        thread.start()
    }
}