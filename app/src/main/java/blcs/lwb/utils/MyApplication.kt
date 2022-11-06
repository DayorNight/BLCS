package blcs.lwb.utils

import android.content.Context
import blcs.lwb.lwbtool.base.BaseApplication
import blcs.lwb.lwbtool.utils.LinNotify
import blcs.lwb.lwbtool.utils.MultiLanguageUtils
import blcs.lwb.utils.greendao.DaoMaster
import blcs.lwb.utils.greendao.DaoMaster.DevOpenHelper
import blcs.lwb.utils.greendao.DaoSession
import org.litepal.LitePal

class MyApplication : BaseApplication() {
    companion object {
        var context: MyApplication? = null
        var daoSession: DaoSession? = null
            private set

    }
    override fun onCreate() {
        super.onCreate()
        context = this
        //多语言设置
        registerActivityLifecycleCallbacks(MultiLanguageUtils.callbacks)
        //通知栏初始化
        LinNotify.setNotificationChannel(context)
        //数据库
        LitePal.initialize(this)
        //GreenDao
        initGreenDao()
        //Room
        initRoom()
    }

    private fun initRoom() {
//       RoomDbManager.build(this);
    }

    private fun initGreenDao() {
        val helper = DevOpenHelper(this, "Blcs2.db")
        val db = helper.writableDatabase
        val daoMaster = DaoMaster(db)
        daoSession = daoMaster.newSession()
    }

    override fun attachBaseContext(base: Context?) {
        //系统语言等设置发生改变时会调用此方法，需要要重置app语言
        super.attachBaseContext(base)
    }


}