package blcs.lwb.lwbtool.retrofit;

import blcs.lwb.lwbtool.bean.BaseResponse;
import blcs.lwb.lwbtool.utils.LogUtils;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 数据返回统一处理  参考https://www.jianshu.com/p/ff619fea7e22
 * @param <T>
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {
    private static final String TAG = "BaseObserver";
    @Override
    public void onNext(BaseResponse<T> response) {
        LogUtils.e("response " +response.toString() );
        LogUtils.e("response " +response.getRes_code() );
        //在这边对 基础数据 进行统一处理  举个例子：
        if(response.getRes_code()==200){
            onSuccess(response.getDemo());
        }else{
            onFailure(null,response.getErr_msg());
        }
    }

    @Override
    public void onError(Throwable e) {//服务器错误信息处理
        onFailure(e, RxExceptionUtil.exceptionHandler(e));
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    public abstract void onSuccess(T result);

    public abstract void onFailure(Throwable e,String errorMsg);

}
