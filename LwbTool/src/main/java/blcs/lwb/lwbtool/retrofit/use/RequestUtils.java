package blcs.lwb.lwbtool.retrofit.use;

import android.content.Context;

import com.trello.rxlifecycle4.components.support.RxFragment;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

import org.reactivestreams.Subscriber;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blcs.lwb.lwbtool.bean.Demo;
import blcs.lwb.lwbtool.bean.VersionBean;
import blcs.lwb.lwbtool.retrofit.MyObserver;
import blcs.lwb.lwbtool.retrofit.RetrofitUtils;
import blcs.lwb.lwbtool.retrofit.RxHelper;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 提交参数方式
 */
public class RequestUtils {

    /**
     * 获取新版本
     */
    public static void getVersion(Context context, MyObserver<VersionBean> observer){
        RetrofitUtils.getApiUrl()
                .getVersion().compose(RxHelper.FlowableIO2Main(context))
                .subscribe(observer);
    }
    /**
     * 版本介绍
     */
    public static void getVersionList(Context context, MyObserver<List<VersionBean>> observer){
        RetrofitUtils.getApiUrl()
                .getVersionList().compose(RxHelper.FlowableIO2Main(context))
                .subscribe(observer);
    }

 /**
     * Get 请求demo
     * @param context
     * @param observer
     */
    public static void getDemo(Context context, MyObserver<Demo> observer){
        RetrofitUtils.getApiUrl()
                .getDemo()
                .compose(RxHelper.FlowableIO2Main(context))
                .subscribe(observer);
    }

    /**
     * Get 请求demo
     * @param context
     * @param observer
     */
    public static void getDemoList(Context context, MyObserver<List<Demo>> observer){
        RetrofitUtils.getApiUrl()
                .getDemoList().compose(RxHelper.FlowableIO2Main(context))
                .subscribe(observer);
    }
    /**
     * Post 请求demo
     * @param context
     * @param consumer
     */
    public static void postDemo(RxAppCompatActivity context, String name, String password, Subscriber<Demo> consumer){
        RetrofitUtils.getApiUrl()
                .postUser(name,password).compose(RxHelper.FlowableIO2Main(context))
                .subscribe(consumer);
    }
    /**
     * Put 请求demo
     * @param context
     * @param consumer
     */
    public static void putDemo(Context context, String access_token,Subscriber<Demo> consumer){
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept","application/json");
        headers.put("Authorization",access_token);
        RetrofitUtils.getApiUrl()
                .put(headers,"厦门").compose(RxHelper.FlowableIO2Main(context))
                .subscribe(consumer);
    }
    /**
     * Delete 请求demo
     * @param context
     * @param consumer
     */
    public static void deleteDemo(Context context, String access_token,Subscriber<Demo> consumer){
        RetrofitUtils.getApiUrl()
                .delete(access_token,1).compose(RxHelper.FlowableIO2Main(context))
                .subscribe(consumer);
    }

    /**
     * 上传图片
     * @param context
     * @param observer
     */
    public static void upImagView(Context context, String  access_token,String str, Subscriber<Demo>  observer){
        File file = new File(str);
//        File file = new File(imgPath);
        Map<String,String> header = new HashMap<String, String>();
        header.put("Accept","application/json");
        header.put("Authorization",access_token);
//        File file =new File(filePath);
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
//        RequestBody requestFile =
//                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), reqFile);
        RetrofitUtils.getApiUrl().uploadImage(header,body).compose(RxHelper.FlowableIO2Main(context))
                .subscribe(observer);
    }

    /**
     * 上传多张图片
     * @param files
     */
    public static void upLoadImg(Context context,String access_token,List<File> files, Subscriber<Demo>  observer1){
        Map<String,String> header = new HashMap<String, String>();
        header.put("Accept","application/json");
        header.put("Authorization",access_token);
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);//表单类型
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/*"), file);
            builder.addFormDataPart("file", file.getName(), photoRequestBody);
        }
        List<MultipartBody.Part> parts = builder.build().parts();
        RetrofitUtils.getApiUrl().uploadImage1(header,parts).compose(RxHelper.FlowableIO2Main(context))
                .subscribe(observer1);
    }
}

