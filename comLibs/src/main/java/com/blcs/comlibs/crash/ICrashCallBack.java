package com.blcs.comlibs.crash;

import android.content.Context;

public interface ICrashCallBack {
    void uncaughtException(Context ctx, Thread t,Throwable e);

}
