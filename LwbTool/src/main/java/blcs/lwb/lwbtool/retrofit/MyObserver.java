package blcs.lwb.lwbtool.retrofit;


import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.disposables.Disposable;

/**
 * Observer加入加载框
 * @param <T>
 */
public abstract class MyObserver<T> extends BaseObserver<T> {
    private final boolean mShowDialog;
    private ProgressDialog dialog;
    private final Context mContext;
    private Subscription subscription;

    public MyObserver(Context context, Boolean showDialog) {
        mContext = context;
        mShowDialog = showDialog;
    }

    public MyObserver(Context context) {
        this(context,true);
    }

    @Override
    public void onSubscribe(Subscription s) {
        this.subscription = s;
        if (!isConnected(mContext)) {
            Toast.makeText(mContext,"未连接网络",Toast.LENGTH_SHORT).show();
            subscription.cancel();
        } else {
            if (dialog == null && mShowDialog == true) {
                dialog = new ProgressDialog(mContext);
                dialog.setMessage("正在加载中");
                dialog.show();
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        subscription.cancel();
        hidDialog();
        super.onError(e);
    }

    @Override
    public void onComplete() {
        subscription.cancel();
        hidDialog();
        super.onComplete();
    }


    public void hidDialog() {
        if (dialog != null && mShowDialog == true)
            dialog.dismiss();
        dialog = null;
    }
    /**
     * 是否有网络连接，不管是wifi还是数据流量
     * @param context
     * @return
     */
    public static boolean isConnected(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null)
        {
            return false;
        }
        boolean available = info.isAvailable();
        return available;
    }
}

