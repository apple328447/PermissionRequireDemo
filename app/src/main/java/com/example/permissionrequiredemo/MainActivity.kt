package com.example.permissionrequiredemo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    private val REQUEST_CALL_PHONE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PermisssionCheck()
    }

    /**
     * 是否已經請求過該權限
     * API < 23 一律回傳 true
     */
    private fun hasPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED
        } else true
    }
    /**
     * 確認是否要請求權限(API > 23)
     * API < 23 一律不用詢問權限
     */
    fun PermisssionCheck() {
        val CALL_PHONE_Permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (CALL_PHONE_Permission != PackageManager.PERMISSION_GRANTED ) {
            //未取得權限，向使用者要求允許權限
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(arrayOf(Manifest.permission.CALL_PHONE),
                    REQUEST_CALL_PHONE)
            }
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            ActivityCompat.requestPermissions(this@JoinCooperationActivity,
//                    arrayOf(Manifest.permission.CALL_PHONE),
//                    REQUEST_EXTERNAL_STORAGE)
//            }
        } else {
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CALL_PHONE -> {
                //如果使用者同意授權後
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {//不同意就跳掉
                    finish()
                }
                return
            }
        }
    }
}
