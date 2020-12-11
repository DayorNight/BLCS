package blcs.lwb.kotlin

import android.app.Application
import android.content.Context
import android.util.Log
import blcs.lwb.kotlin.Crash.Cockroach
import com.github.moduth.blockcanary.BlockCanary
import com.tencent.mmkv.MMKV

class BlcsApp : Application() {
    companion object {
        var app: Application? = null
        fun getContext(): Context {
            return app!!
        }

        private const val TAG = "BlcsApp"
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        /**
         * 异常捕获上传
         */
        Cockroach.init(this,null)
        /**
         * 卡顿初始化
         */
        BlockCanary.install(this, AppBlockCanaryMr()).start()
        /**
         * MMKV
         */
        val rootDir: String = MMKV.initialize(this)
        Log.e(Companion.TAG, "onCreate: $rootDir")


    }
}