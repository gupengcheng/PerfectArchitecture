package com.gpc.perfectarchitecture.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.gpc.perfectarchitecture.model.db.entity.Lunyu
import com.gpc.perfectarchitecture.model.db.entity.PoemAuthor
import com.gpc.perfectarchitecture.model.db.entity.Poems
import com.gpc.perfectarchitecture.model.db.helper.GreenDaoHelper
import com.gpc.perfectarchitecture.utils.Constant
import com.gpc.perfectarchitecture.utils.LogUtil
import com.gpc.perfectarchitecture.utils.UnZipUtil
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

/*
* @NAME: DataUnZipViewModel
* @Package: com.gpc.perfectarchitecture.viewmodel
* @PoemAuthor : pcg
* @Create at : 2018/11/12 下午5:09
* @Description: 解压文件
*/
class DataUnZipViewModel : ViewModel() {

    private lateinit var mDataUnZiped: MutableLiveData<Boolean>

    fun unZipData(fileDir: String, inputStream: InputStream): LiveData<Boolean> {
        if (!::mDataUnZiped.isInitialized) {
            mDataUnZiped = MutableLiveData()
            processZipData(fileDir, inputStream)
        }
        return mDataUnZiped
    }

    private fun processZipData(fileDir: String, inputStream: InputStream) {
        LogUtil.e("processZipData start 线程是" + Thread.currentThread().name)
        Observable.create(ObservableOnSubscribe<Boolean> { it ->
            LogUtil.e("processZipData begin 线程是" + Thread.currentThread().name)
            val sdCardDirectoryFile = File(fileDir)
            if (!sdCardDirectoryFile.exists()) {
                sdCardDirectoryFile.mkdirs()
            }
            LogUtil.e("mkdirResult")
            var zipFile = File(fileDir + File.separator + Constant.SDCARD_FILE_NAME)
            var fileOutPutStream = FileOutputStream(zipFile)
            val bytes = ByteArray(1024)
            var length: Int = inputStream.read(bytes)
            while (length >= 0) {
                fileOutPutStream.write(bytes, 0, length)
                length = inputStream.read(bytes)
            }
//        fileOutPutStream.write(inputStream.read())
            fileOutPutStream.close()
            LogUtil.e("processZipData done 线程是" + Thread.currentThread().name)
            var zipResult = UnZipUtil.unZipFile(zipFile.absolutePath, fileDir)
            LogUtil.e("zip result = ${zipResult}")
            it.onNext(zipResult)
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer<Boolean> {
                LogUtil.e("${it} result")
                openDb()
            })
    }

    fun openDb() {
        Observable.create(ObservableOnSubscribe<List<Poems>> {
            val authorList = GreenDaoHelper.getInstance().queryPoem(1, 20)
            it.onNext(authorList)
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer<List<Poems>> {
                it.forEach { LogUtil.e("作者:${it.author} , 简介:${it.content}, 朝代：${it.dynasty}") }
            })

        Observable.create(ObservableOnSubscribe<List<PoemAuthor>> {
            val authorList = GreenDaoHelper.getInstance().queryAuthor(1, 20)
            it.onNext(authorList)
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer<List<PoemAuthor>> {
                it.forEach { LogUtil.e("name:${it.name} , desc:${it.desc}, 朝代：${it.dynasty}") }
            })

        Observable.create(ObservableOnSubscribe<List<Lunyu>> {
            val authorList = GreenDaoHelper.getInstance().queryLunYu(1, 20)
            it.onNext(authorList)
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer<List<Lunyu>> {
                it.forEach { LogUtil.e("lunyu :${it.content} , chapter:${it.chapter}") }
            })
    }

}