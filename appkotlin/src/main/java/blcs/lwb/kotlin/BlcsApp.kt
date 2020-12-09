package blcs.lwb.kotlin

import android.app.Application
import android.content.Context
import com.github.moduth.blockcanary.BlockCanary

class BlcsApp : Application() {
    companion object {
        var app: Application? = null
        fun getContext(): Context {
            return app!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        // 卡顿初始化
        BlockCanary.install(this, AppBlockCanaryMr()).start()

    }
}