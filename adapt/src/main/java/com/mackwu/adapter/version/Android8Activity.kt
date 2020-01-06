package com.mackwu.adapter.version

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.mackwu.adapter.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * ===================================================
 * Created by MackWu on 2019/12/30 10:42
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class Android8Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0x01)
        }
        btn_test.setOnClickListener { AssetApkInstaller.requestPackageInstalls(this, assetName = "inform_tool.aac") }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("TAG", "onActivityResult...  requestCode: $requestCode, resultCode: $resultCode")
        if (resultCode == Activity.RESULT_OK && requestCode == AssetApkInstaller.INSTALL_REQUEST_CODE) {
            AssetApkInstaller.requestPackageInstalls(this, assetName = "inform_tool.aac")
        }
    }
}