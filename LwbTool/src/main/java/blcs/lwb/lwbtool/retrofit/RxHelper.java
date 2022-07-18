package blcs.lwb.lwbtool.retrofit;



import android.content.Context;

import com.trello.rxlifecycle4.android.ActivityEvent;
import com.trello.rxlifecycle4.components.RxActivity;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle4.components.support.RxFragment;
import com.trello.rxlifecycle4.components.support.RxFragmentActivity;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableTransformer;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 调度类
 */
public class RxHelper {

    public static <T> FlowableTransformer<T, T> FlowableIO2Main(final Context context) {
        return upstream -> {
            Flowable<T> observable = upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            return composeContext(context, observable);
        };
    }

//    public static <T> ObservableTransformer<T, T> observableIO2Main(final Context context) {
//        return upstream -> {
//            Observable<T> observable = upstream.subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread());
//            return composeContext(context, observable);
//        };
//    }
//
//    public static <T> ObservableTransformer<T, T> observableIO2Main(final RxFragment fragment) {
//        return upstream -> upstream.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).compose(fragment.<T>bindToLifecycle());
//    }

//    public static <T> FlowableTransformer<T, T> flowableIO2Main() {
//        return upstream -> upstream
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }

    private static <T> Flowable<T> composeContext(Context context, Flowable<T> flowable) {
        if(context instanceof RxActivity) {
            return flowable.compose(((RxActivity) context).bindUntilEvent(ActivityEvent.DESTROY));
        } else if(context instanceof RxFragmentActivity){
            return flowable.compose(((RxFragmentActivity) context).bindUntilEvent(ActivityEvent.DESTROY));
        }else if(context instanceof RxAppCompatActivity){
            return flowable.compose(((RxAppCompatActivity) context).bindUntilEvent(ActivityEvent.DESTROY));
        }else {
            return flowable;
        }
    }
}
