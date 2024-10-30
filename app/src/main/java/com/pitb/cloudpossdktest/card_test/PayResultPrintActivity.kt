package com.pitb.cloudpossdktest.card_test

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cloudpos.DeviceException
import com.cloudpos.POSTerminal
import com.cloudpos.printer.Format
import com.cloudpos.printer.PrinterDevice
import com.pitb.cloudpossdktest.R
import com.pitb.cloudpossdktest.databinding.ActivityPayResultPrintBinding
import com.pitb.cloudpossdktest.printer.PrintTag
import com.pitb.cloudpossdktest.printer.PurchaseBillEntity
import com.pitb.cloudpossdktest.threads.WaitThread


class PayResultPrintActivity : AppCompatActivity() {

    var payType = -1
    var isRun = false
    private lateinit var device: PrinterDevice
    private lateinit var binding: ActivityPayResultPrintBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPayResultPrintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initParameters()
        initViews()
    }

    private fun initParameters() {
        payType = intent.getIntExtra(CardActivity.PAY_TYPE, -1)
        device = POSTerminal.getInstance(this@PayResultPrintActivity)
            .getDevice("cloudpos.device.printer") as PrinterDevice
    }

    private fun initViews() {
        when (payType) {
            CardActivity.TYPE_CONTACTLESS -> {
                binding.txt0.append(getString(R.string.RFCard))
            }

            CardActivity.TYPE_MSR -> {
                binding.txt0.append(getString(R.string.MSR))
            }

            CardActivity.TYPE_IC -> {
                binding.txt0.append(getString(R.string.ICCard))
            }

            else -> {}
        }

        binding.btn0.setOnClickListener {
            if (isRun) {
                return@setOnClickListener
            }
            val thread = WaitThread(this@PayResultPrintActivity, device)
            thread.start()
        }

        binding.btn1.setOnClickListener {
            finish()
        }
    }

    fun setPurchaseBillEntity(purchaseBill: PurchaseBillEntity) {
        purchaseBill.merchantName = "人民商场/REN MIN STORE"
        purchaseBill.merchantNo = "800201020800201"
        purchaseBill.terminalNo = "20063201"
        purchaseBill.operator = "01"
        purchaseBill.cardNumber = "5359 18** **** 8888   MCC"
        purchaseBill.issNo = "01021000"
        purchaseBill.acqNo = "01031000"
        purchaseBill.txnType = "消费/SALE"
        purchaseBill.expDate = "2006/12"
        purchaseBill.batchNo = "000122"
        purchaseBill.voucherNo = "105233"
        purchaseBill.authNo = "384928"
        purchaseBill.dataTime = "2005/01/31 19:20:18"
        purchaseBill.refNo = "123456123456"
        purchaseBill.amout = "RMB 1234.56"
        // purchaseBill.setTips("RMB 123.56");
        // purchaseBill.setTotal("RMB 1358.12");
        purchaseBill.reference = "重打印凭证/DUPLICATD"
    }

    @Throws(DeviceException::class)
    fun printBill(purchaseBill: PurchaseBillEntity) {
        var format: Format = Format()
        format.setParameter("bold", "true")
        format.setParameter("size", "large")
        format.setParameter("align", "center")
        device.printlnText(format, PrintTag.PurchaseBillTag.PURCHASE_BILL_TITLE)
        format.clear()
        format = device.getDefaultParameters()
        device.printlnText(
            format, PrintTag.PurchaseBillTag.MERCHANT_NAME_TAG
                    + purchaseBill.merchantName
        )
        device.printlnText(
            PrintTag.PurchaseBillTag.MERCHANT_NO_TAG
                    + purchaseBill.merchantNo
        )
        device.printlnText(
            PrintTag.PurchaseBillTag.TERMINAL_NO_TAG
                    + purchaseBill.terminalNo
        )
        device.printlnText(PrintTag.PurchaseBillTag.OPERATOR_TAG + purchaseBill.operator)
        device.printlnText(PrintTag.PurchaseBillTag.ISS_NO_TAG + purchaseBill.issNo)
        device.printlnText(PrintTag.PurchaseBillTag.ACQ_NO_TAG + purchaseBill.acqNo)
        device.printlnText(
            (PrintTag.PurchaseBillTag.CARD_NUMBER_TAG
                    + purchaseBill.cardNumber)
        )
        device.printlnText(PrintTag.PurchaseBillTag.TXN_TYPE_TAG + purchaseBill.txnType)
        device.printlnText(PrintTag.PurchaseBillTag.EXP_DATE_TAG + purchaseBill.expDate)
        device.printlnText(PrintTag.PurchaseBillTag.BATCH_NO_TAG + purchaseBill.batchNo)
        device.printlnText(PrintTag.PurchaseBillTag.VOUCHER_NO_TAG + purchaseBill.voucherNo)
        device.printlnText(PrintTag.PurchaseBillTag.AUTH_NO_TAG + purchaseBill.authNo)
        device.printlnText(PrintTag.PurchaseBillTag.REF_NO_TAG + purchaseBill.refNo)
        device.printlnText(PrintTag.PurchaseBillTag.DATE_TIME_TAG + purchaseBill.dataTime)
        device.printlnText(PrintTag.PurchaseBillTag.AMOUT_TAG + purchaseBill.amout)
        device.sendESCCommand("\n".toByteArray())
        device.printlnText(PrintTag.PurchaseBillTag.REFERENCE_TAG + purchaseBill.reference)
        device.sendESCCommand("\n".toByteArray())
        device.printlnText(PrintTag.PurchaseBillTag.C_CARD_HOLDER_SIGNATURE_TAG)
        device.printlnText(PrintTag.PurchaseBillTag.E_CARD_HOLDER_SIGNATURE_TAG)
        device.sendESCCommand("\n\n".toByteArray())
        device.printlnText(PrintTag.PurchaseBillTag.C_AGREE_TRADE_TAG)
        device.printlnText(PrintTag.PurchaseBillTag.E_AGREE_TRADE_TAG)
        device.sendESCCommand("\n\n".toByteArray())
    }
}