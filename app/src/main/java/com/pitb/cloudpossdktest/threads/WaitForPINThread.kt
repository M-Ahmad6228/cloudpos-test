package com.pitb.cloudpossdktest.threads

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.cloudpos.OperationResult
import com.cloudpos.POSTerminal
import com.cloudpos.TimeConstants
import com.cloudpos.jniinterface.PinPadCallbackHandler
import com.cloudpos.pinpad.KeyInfo
import com.cloudpos.pinpad.PINPadDevice
import com.cloudpos.pinpad.PINPadOperationResult
import com.cloudpos.sdk.util.StringUtility
import com.pitb.cloudpossdktest.R
import com.pitb.cloudpossdktest.card_test.CardActivity
import com.pitb.cloudpossdktest.card_test.PayResultPrintActivity


class WaitForPINThread(val activity: Activity) : Thread() {
    override fun run() {
        val device =
            POSTerminal.getInstance(activity).getDevice("cloudpos.device.pinpad") as PINPadDevice
        try {
            device.open()

            val info = KeyInfo(2, 0, 0, 5)

            val operationResult = device.waitForPinBlock(
                info,
                "1234567890123456", false, TimeConstants.FOREVER
            )
            if (operationResult.resultCode == OperationResult.SUCCESS) {
                Log.e(
                    "PINBlock", StringUtility
                        .ByteArrayToString(
                            operationResult.encryptedPINBlock,
                            operationResult.encryptedPINBlock.size
                        )
                )
                val intent = Intent()
                intent.setClass(activity, PayResultPrintActivity::class.java)
                intent.putExtra(
                    CardActivity.PAY_TYPE,
                    activity.intent.getIntExtra(CardActivity.PAY_TYPE, -1)
                )
                activity.startActivity(intent)
                activity.finish()
            }
//            val intent = Intent()
//            intent.setClass(activity, PayResultPrintActivity::class.java)
//            intent.putExtra(
//                CardActivity.PAY_TYPE,
//                activity.intent.getIntExtra(CardActivity.PAY_TYPE, -1)
//            )
//            activity.startActivity(intent)
//            activity.finish()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                device.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}