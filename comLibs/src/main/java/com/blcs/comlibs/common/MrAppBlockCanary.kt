package com.blcs.comlibs.common

import com.github.moduth.blockcanary.BlockCanaryContext
import java.io.File

/**
 * 卡顿管理
 */
class MrAppBlockCanary : BlockCanaryContext() {
    /**
     * 设置限定符
     */
    override fun provideQualifier(): String? {
        return AppUtils.getAppName(provideContext())+"V"+AppUtils.getVersionName(provideContext())
    }
    /**
     * 设置UID
     */
    override fun provideUid(): String {
        return AppUtils.getAppUid(provideContext(),AppUtils.getAppPackageName(provideContext())).toString()
    }

    /**
     * 设置网络状态
     */
    override fun provideNetworkType(): String {
        return LinNetStatus.getNetWorkType(provideContext())
    }

    /**
     * 配置监视器持续时间
     */
    override fun provideMonitorDuration(): Int {
        return super.provideMonitorDuration()
    }

    /**
     * 配置保存路径
     */
    override fun providePath(): String {
        return super.providePath()
    }

    /**
     * 日志保存
     */
    override fun upload(zippedFile: File?) {
        super.upload(zippedFile)
    }

    /**
     * 白名单
     */
    override fun provideWhiteList(): MutableList<String> {
        return super.provideWhiteList()
    }
}