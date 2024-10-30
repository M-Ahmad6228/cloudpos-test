package com.pitb.cloudpossdktest.threads

import android.content.Context
import android.util.Log
import com.cloudpos.DeviceException
import com.cloudpos.printer.PrinterDevice
import com.pitb.cloudpossdktest.R
import com.pitb.cloudpossdktest.card_test.PayResultPrintActivity
import com.pitb.cloudpossdktest.printer.PurchaseBillEntity

class WaitThread(val activity: PayResultPrintActivity, val device: PrinterDevice) :
    Thread() {
    override fun run() {
        try {
            device.open()
            // queryStatus
            val status: Int = device.queryStatus()
            if (status == PrinterDevice.STATUS_OUT_OF_PAPER) {
                Log.e("TAG", activity.getString(R.string.NoPaper))
                throw DeviceException(DeviceException.GENERAL_EXCEPTION)
            }

            val purchaseBill = PurchaseBillEntity()
            activity.setPurchaseBillEntity(purchaseBill)
            activity.printBill(purchaseBill)
        } catch (e: DeviceException) {
            e.printStackTrace()
        } finally {
            try {
                device.close()
            } catch (e: DeviceException) {
                e.printStackTrace()
            }
        }
        activity.isRun = false
    }
}