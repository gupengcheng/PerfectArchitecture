package com.gpc.perfectarchitecture.view

import android.Manifest
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Environment
import com.gpc.perfectarchitecture.R
import com.gpc.perfectarchitecture.base.BaseActivity
import com.gpc.perfectarchitecture.utils.Constant
import com.gpc.perfectarchitecture.utils.LogUtil
import com.gpc.perfectarchitecture.viewmodel.DataUnZipViewModel
import com.tbruyelle.rxpermissions2.RxPermissions
import java.io.File

/*
* @NAME: SplashActivity
* @Package: com.gpc.perfectarchitecture.view
* @PoemAuthor : pcg
* @Create at : 2018/11/12 下午3:52
* @Description: 闪屏页
*/
class SplashActivity : BaseActivity() {

    private lateinit var mDataUnZipViewModel: DataUnZipViewModel
    private lateinit var mRxPermission: RxPermissions

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun obtainViewModel() {
        mDataUnZipViewModel = ViewModelProviders.of(this).get(DataUnZipViewModel::class.java)
    }

    override fun initActivity() {
        mRxPermission = RxPermissions(this)
        mRxPermission.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
            .subscribe({ granted ->
                if (granted) {
                    LogUtil.e("granted")
                    val fileDir =
                        Environment.getExternalStorageDirectory().absolutePath + File.separator + Constant.SDCARD_FILE_DIRECTORY_NAME
                    val sdCardDirectoryFile = File(fileDir)
                    if (sdCardDirectoryFile.exists()) {
                        var lunyuFile = File(fileDir + File.separator + Constant.LUNYU_FILE_NAME)
                        var authorFile = File(fileDir + File.separator + Constant.POEM_AUTHOR_FILE_NAME)
                        var poemFile = File(fileDir + File.separator + Constant.POEM_FILE_NAME)

                        if (lunyuFile.exists() && poemFile.exists() && authorFile.exists()) {
                            mDataUnZipViewModel.openDb()
                            return@subscribe
                        }
                    }
                    unZipFile()
                } else {
                    LogUtil.e("not granted")
                }
            })
    }

    fun unZipFile() {
        val zipInputStream = assets.open("data.zip")
        val fileDir =
            Environment.getExternalStorageDirectory().absolutePath + File.separator + Constant.SDCARD_FILE_DIRECTORY_NAME
        mDataUnZipViewModel.unZipData(fileDir, zipInputStream).observe(this, Observer<Boolean> { dataUnZiped ->
            LogUtil.e("dataUnZiped = ${dataUnZiped}")
        })
    }

}