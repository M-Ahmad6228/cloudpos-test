package com.pitb.cloudpossdktest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pitb.cloudpossdktest.card_test.CardActivity
import com.pitb.cloudpossdktest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardTest.setOnClickListener {
            startActivity(Intent(this, CardActivity::class.java))
        }

//        launchScanning(
//            "Ahmad",
//            "ahmad",
//            "123456",
//            "3520213134123",
//            null,
//            FingerprintConfig.Mode.ENROLL
//        )
    }

//    fun launchScanning(
//        name: String?,
//        username: String?,
//        password: String?,
//        cnicNumber: String?,
//        nadraConfig: NadraConfig?,
//        mode: FingerprintConfig.Mode
//    ) {
//        try {
//            val themeColor = ResourcesCompat.getColor(
//                resources, R.color.black, null
//            ) // changed color in color file
//            val accentColor = ResourcesCompat.getColor(
//                resources, R.color.white, null
//            ) // changed color in color file
////            FaceoffCustomization.setCameraScreenLeftAndRightHandInfoColor(resources.getColor(R.color.white))
////            val customUI = CustomUI().setShowGuidanceScreen(true).setAppBarTextColor(accentColor)
////                .setAppBarColor(themeColor).setIconsColor(accentColor)
////                .setGuidanceScreenBackgroundColor(accentColor)
////                .setGuidanceScreenButtonColor(themeColor)
////                .setGuidanceScreenButtonTextColor(accentColor)
////                .setCameraScreenMessageTextColor(accentColor).setGuidanceScreenFullWidthButton(true)
////                .setGuidanceScreenButtonText("Continue")
////                .setGuidanceScreenInstructionTextColor(Color.BLACK).setAppBarBackgroundImage(0)
////                .setGuidanceScreenInstructionText("Line up your hand with the guide\nKeep your fingers together\nThen stay still.")
////                .setGuidanceScreenAnimation(0).setGuidanceScreenInstructionImage(0)
////                .setGuidanceScreenAppBarTitle("Guidance").setSplashScreenLoaderColor(themeColor)
////                .setSplashScreenMessageColor(themeColor).setSplashScreenMessage("Please Wait...");
////
////            val customDialog =
////                CustomDialog().setDialogBackgroundColor(Color.WHITE).setDialogTitleColor(themeColor)
////                    .setDialogMessageColor(Color.GRAY).setDialogImageBackgroundColor(themeColor)
////                    .setDialogImageForegroundColor(themeColor)
////                    .setDialogButtonBackgroundColor(themeColor)
////                    .setDialogButtonTextColor(accentColor).setDialogSuccessImage(0)
////                    .setDialogSwitchHandImage(0).setDialogErrorImage(0)
////                    .setSuccessDialogTitle("Success").setSuccessDialogMessage("Scan Complete")
////                    .setSuccessDialogButtonText("Change hand").setSwitchHandDialogTitle("Success!")
////                    .setSwitchHandDialogMessage("Please change hand")
////                    .setSwitchHandDialogButtonText("Next").setErrorDialogTitle("Please try again");
//
//            val builder =
//                FingerprintConfig.Builder().setFingers(FingerprintConfig.Fingers.EIGHT_FINGERS)
//                    .setMode(mode).setLiveness(FingerprintConfig.Liveness.STRONG).setPackPng(true)
////                    .setCustomUI(customUI)
////                    .setCustomDialog(customDialog)
//            if (nadraConfig != null) {
//                builder.setNadraConfig(nadraConfig)
//            }
//            val fingerprintConfig = builder.build(0, 0)
//            val intent = Intent(this, FingerprintScannerActivity::class.java)
//            if (mode == FingerprintConfig.Mode.ENROLL) {
//                intent.putExtra(FingerprintScannerActivity.NAME_FOR_FINGERPRINT, name)
//                intent.putExtra(FingerprintScannerActivity.CNIC_FOR_FINGERPRINT, cnicNumber)
//            }
//            if (mode == FingerprintConfig.Mode.NADRA) {
//                intent.putExtra(FingerprintScannerActivity.USERNAME, username)
//                intent.putExtra(FingerprintScannerActivity.PASSWORD, password)
//                intent.putExtra(FingerprintScannerActivity.CNIC_FOR_FINGERPRINT, cnicNumber)
//            }
//            intent.putExtra(
//                FingerprintScannerActivity.FACEOFF_FINGERPRINT_CONFIG, fingerprintConfig
//            )
//            startActivityForResult(intent, 22)
//        } catch (e: LivenessNotSupportedException) {
//            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == 22 && data != null) {
//            //   binding.btnSubmit.show()
//            // Getting fingerprintResponse from intent
//            val responseCode =
//                data.getIntExtra(FingerprintScannerActivity.FINGERPRINT_RESPONSE_CODE, -1)
//            if (responseCode > 0) {
//                val fingerprintResponse =
//                    ResultIPC.getInstance().getFingerprintResponse(responseCode)
//                if (fingerprintResponse != null) {
//                    // If scanning and NADRA request were successful
//                    if (resultCode == FingerprintResponse.Response.SUCCESS_WSQ_EXPORT.responseCode || resultCode == FingerprintResponse.Response.SUCCESS_PNG_EXPORT.responseCode) {
//                        // binding.layout2.show()]
//                        showFingerprints(responseCode)
//                    }
//                } else {
//                    Toast.makeText(this, "Fingerprint Response is null!", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            } else {
//                Toast.makeText(this, "Could not receive response!", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    fun showFingerprints(responseCode: Int) {
//        if (responseCode > 0) {
//            val fingerprintResponse = ResultIPC.getInstance().getFingerprintResponse(responseCode)
//            if (fingerprintResponse != null && fingerprintResponse.pngList != null && !fingerprintResponse.pngList.isEmpty()) {
//                for (png in fingerprintResponse.pngList) {
//                    //saveWsqToLocalStorage(ViewFingerprintActivity.this, png.getBinaryBase64ObjectPNG(), String.valueOf(png.getFingerPositionCode()), "Fingerprints/PNG/");
//                    val binaryBase64ObjectPNG = png.binaryBase64ObjectPNG
////                    if (binaryBase64ObjectPNG != null && !binaryBase64ObjectPNG.isEmpty()) {
////                        val decodedString = Base64.decode(binaryBase64ObjectPNG, Base64.DEFAULT)
////                        val decodedImage =
////                            BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
//////                        setBitmap(decodedImage, png.getFingerPositionCode());
////                    }
////                    setData(binaryBase64ObjectPNG, png.fingerPositionCode)
//                }
//
////                UtilsFunction.showLoaderDialog(requireActivity())
////                viewModel.nadraVerification(cnic, mobile, area, latitude, longitude, imei)
////                observeSingleEvent(viewModel.uiEvents) { data ->
////                    when (data) {
////                        is NadraVerificationEvent.VerificationSuccess -> {
////                            if (data.response.responseCode != null) {
////                                when (data.response.responseCode) {
////                                    "100" -> {
////                                        sendFingerPrints(
////                                            data.response.responseDescription,
////                                            "",
////                                            "",
////                                            true,
////                                            data.response.responseDescription,
////                                            data.response.responseCode.toString()
////                                        )
////                                    }
////
////                                    else -> {
////                                        sendFingerPrints(
////                                            "",
////                                            data.response.responseDescription,
////                                            data.response.responseCode.toString(),
////                                            false,
////                                            data.response.responseDescription,
////                                            data.response.responseCode.toString()
////                                        )
////                                    }
////                                }
////                            } else sendFingerPrints(
////                                "", "", "", false, "", "500"
////                            )
////                        }
////
////                        is NadraVerificationEvent.VerificationFailure -> {
////                            sendFingerPrints(
////                                "", "", "", false, "", "500"
////                            )
////                        }
////
////                        is NadraVerificationEvent.BlinkSaveRequest -> {
////                            saveBlink(data.response, data.bvssuccess, data.error)
////                        }
////                    }
////                }
//
//            } else {
//                Toast.makeText(
//                    this,
//                    "No fingerprints were captured or PNGs were not packed!",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        } else {
//            Toast.makeText(this, "No fingerprints captured", Toast.LENGTH_SHORT).show()
//        }
//    }
}