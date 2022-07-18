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
     * 日志保存
     */
    override fun upload(zippedFile: File?) {
        super.upload(zippedFile)
    }

}