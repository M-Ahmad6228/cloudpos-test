package com.pitb.cloudpossdktest.threads

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.cloudpos.Device
import com.cloudpos.OperationResult
import com.cloudpos.TimeConstants
import com.cloudpos.msr.MSRDevice
import com.cloudpos.msr.MSRTrackData
import com.pitb.cloudpossdktest.card_test.CardActivity
import com.pitb.cloudpossdktest.card_test.InputPINActivity

class MSRThread(val device: Device, val activity: Activity, val payType: Int) : Thread() {
    override fun run() {
        try {
            device.open()
            val operationResult = (device as MSRDevice)
                .waitForSwipe(TimeConstants.FOREVER)
            if (operationResult.resultCode == OperationResult.SUCCESS) {
                val trackData = operationResult.msrTrackData
                for (trackNo in 0..2) {
                    if (trackData.getTrackError(trackNo) != MSRTrackData.NO_ERROR) {
                        Log.e(
                            "MSRThread",
                            String.format(
                                "Track(%s)",
                                (trackNo + 1)
                            ) + String.format(
                                "ErrorMessage%s",
                                trackData.getTrackError(trackNo)
                            )
                        )
                    } else {
                        Log.e(
                            "MSRThread",
                            String.format("Track(%s)", (trackNo + 1)) + String.format(
                                "TrackData%s", String(
                                    trackData.getTrackData(trackNo)
                                )
                            )
                        )
                    }
                }
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