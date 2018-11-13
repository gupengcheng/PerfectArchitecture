package com.gpc.perfectarchitecture.utils

import android.util.Log
import com.gpc.perfectarchitecture.BuildConfig

/*
* @NAME: LogUtil
* @Package: com.gpc.perfectarchitecture.utils
* @Author : pcg
* @Create at : 2018/11/12 下午5:44
* @Description:
*/
class LogUtil {

    companion object {
        private val DEBUG = BuildConfig.DEBUG
        private val DEFAULT_TAG = "pcg"

        @JvmStatic
        fun e(tag: String, msg: String) {
            if (!DEBUG) {
                return
            }
            Log.e(tag, msg)
        }

        @JvmStatic
        fun e(msg: String) {
            if (!DEBUG) {
                return
            }
            Log.e(DEFAULT_TAG, msg)
        }

        @JvmStatic
        fun d(tag: String, msg: String) {
            if (!DEBUG) {
                return
            }
            Log.d(tag, msg)
        }

        @JvmStatic
        fun d(msg: String) {
            if (!DEBUG) {
                return
            }
            Log.d(DEFAULT_TAG, msg)
        }

        @JvmStatic
        fun i(tag: String, msg: String) {
            if (!DEBUG) {
                return
            }
            Log.i(tag, msg)
        }

        @JvmStatic
        fun i(msg: String) {
            if (!DEBUG) {
                return
            }
            Log.i(DEFAULT_TAG, msg)
        }

        @JvmStatic
        fun w(tag: String, msg: String) {
            if (!DEBUG) {
                return
            }
            Log.w(tag, msg)
        }

        @JvmStatic
        fun w(msg: String) {
            if (!DEBUG) {
                return
            }
            Log.w(DEFAULT_TAG, msg)
        }

        @JvmStatic
        fun v(tag: String, msg: String) {
            if (!DEBUG) {
                return
            }
            Log.v(tag, msg)
        }

        @JvmStatic
        fun v(msg: String) {
            if (!DEBUG) {
                return
            }
            Log.v(DEFAULT_TAG, msg)
        }

    }

}