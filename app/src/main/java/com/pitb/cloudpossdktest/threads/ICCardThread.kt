package com.pitb.cloudpossdktest.threads

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.cloudpos.Device
import com.cloudpos.OperationResult
import com.cloudpos.TimeConstants
import com.cloudpos.card.CPUCard
import com.cloudpos.sdk.util.StringUtility
import com.cloudpos.smartcardreader.SmartCardReaderDevice
import com.pitb.cloudpossdktest.card_test.CardActivity
import com.pitb.cloudpossdktest.card_test.InputPINActivity

class ICCardThread(val device: Device, val activity: Activity, val payType: Int) : Thread() {
    override fun run() {
        try {
            device.open()
            val operationResult =
                (device as SmartCardReaderDevice).waitForCardPresent(TimeConstants.FOREVER)
            if (operationResult.resultCode == OperationResult.SUCCESS) {
                val card = operationResult.card
                val dataATR = (card as CPUCard).connect()
                Log.e(
                    "ICCardThread",
                    "ATR = " + StringUtility.ByteArrayToString(dataATR.bytes, dataATR.bytes.size)
                )
                card.disconnect()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                device.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        val intent = Intent()
        intent.setClass(activity, InputPINActivity::class.java)
        intent.putExtra(CardActivity.PAY_TYPE, payType)
        activity.startActivity(intent)
        activity.finish()
    }
}