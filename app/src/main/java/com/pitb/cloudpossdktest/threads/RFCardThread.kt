package com.pitb.cloudpossdktest.threads

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.cloudpos.Device
import com.cloudpos.OperationResult
import com.cloudpos.TimeConstants
import com.cloudpos.rfcardreader.RFCardReaderDevice
import com.cloudpos.sdk.util.StringUtility
import com.pitb.cloudpossdktest.card_test.CardActivity
import com.pitb.cloudpossdktest.card_test.InputPINActivity

class RFCardThread(val device: Device, val activity: Activity, val payType: Int) : Thread() {

    override fun run() {
        try {
            device.open()
            val operationResult =
                (device as RFCardReaderDevice).waitForCardPresent(TimeConstants.FOREVER)
            if (operationResult.resultCode == OperationResult.SUCCESS) {
                val cardID = operationResult.card.id
                Log.e(
                    "RFCardThread",
                    "CardID = " + StringUtility.ByteArrayToString(cardID, cardID.size)
                )
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