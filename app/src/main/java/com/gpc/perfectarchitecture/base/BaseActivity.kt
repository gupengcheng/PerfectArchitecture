package com.gpc.perfectarchitecture.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/*
* @NAME: BaseActivity
* @Package: com.gpc.perfectarchitecture.base
* @PoemAuthor : pcg
* @Create at : 2018/11/12 下午3:52
* @Description: 所有activity的基类
*/
abstract class BaseActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        obtainViewModel()
        initActivity()
    }

    abstract fun getLayoutId():Int

    abstract fun initActivity()

    abstract fun obtainViewModel()

}