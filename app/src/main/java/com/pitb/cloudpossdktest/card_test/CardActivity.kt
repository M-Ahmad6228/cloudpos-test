package com.pitb.cloudpossdktest.card_test

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pitb.cloudpossdktest.R
import com.pitb.cloudpossdktest.databinding.ActivityCardBinding


class CardActivity : AppCompatActivity() {

    companion object {
        val PAY_TYPE: String = "PAYTYPE"
        val TYPE_CONTACTLESS: Int = 0
        val TYPE_MSR: Int = 1
        val TYPE_IC: Int = 2
    }

    private lateinit var binding: ActivityCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initParameters()
        initViews()
    }

    private fun initViews() {
        binding.btn0.setOnClickListener {
            when (binding.rdoGroup.checkedRadioButtonId) {
                R.id.rdo_btn_0 -> intent.putExtra(PAY_TYPE, TYPE_CONTACTLESS)
                R.id.rdo_btn_1 -> intent.putExtra(PAY_TYPE, TYPE_MSR)
                R.id.rdo_btn_2 -> intent.putExtra(PAY_TYPE, TYPE_IC)
                else -> {}
            }
            startActivity(intent)
        }
    }

    private fun initParameters() {
        intent = Intent()
        intent.setClass(this, CardManagerActivity::class.java)
    }
}