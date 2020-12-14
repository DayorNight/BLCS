package blcs.lwb.kotlin.Crash;

import android.content.Context;

public interface ICrashCallBack {
    void uncaughtException(Context ctx, Thread t,Throwable e);

}
