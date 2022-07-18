package blcs.lwb.lwbtool.retrofit.use;

import java.util.List;
import java.util.Map;

import blcs.lwb.lwbtool.Constants;
import blcs.lwb.lwbtool.bean.Bean;
import blcs.lwb.lwbtool.bean.Demo;
import blcs.lwb.lwbtool.bean.VersionBean;
import blcs.lwb.lwbtool.bean.BaseResponse;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.Deferred;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface ApiUrl {
    /**
     * 有效链接
     */
    @GET(Constants.retrofit)
    Call<Bean> getRetrofit();

    @GET(Constants.retrofit)
    Flowable<BaseResponse<Demo>> getDemo();

    @GET(Constants.retrofitList)
    Flowable<BaseResponse<List<Demo>>> getDemoList();

    @GET(Constants.version)
    Flowable<BaseResponse<List<VersionBean>>> getVersionList();

    @GET(Constants.versionLast)
    Flowable<BaseResponse<VersionBean>> getVersion();

    /**
     * TODO Get请求
     */
    //第一种方式：GET不带参数
    @GET("retrofit.txt")
    Flowable<BaseResponse<Demo>> getUser();
    @GET
    Flowable<Demo> getUser(@Url String url);
    @GET
    Flowable<Demo> getUser1(@Url String url); //简洁方式   直接获取所需数据
    //第二种方式：GET带参数
    @GET("api/data/{type}/{count}/{page}")
    Flowable<Demo> getUser(@Path("type") String type, @Path("count") int count, @Path("page") int page);
    //第三种方式：GET带请求参数：https://api.github.com/users/whatever?client_id=xxxx&client_secret=yyyy
    @GET("users/whatever")
    Flowable<Demo> getUser(@Query("client_id") String id, @Query("client_secret") String secret);
    @GET("users/whatever")
    Flowable<Demo> getUser(@QueryMap Map<String, String> info);

    /**
     * TODO POST请求
     */
    //第一种方式：@Body
    @Headers("Accept:application/json")
    @POST("login")
    Flowable<Demo> postUser(@Body RequestBody body);
    //第二种方式：@Field

    @Headers("Accept:application/json")
    @POST("auth/login")
    @FormUrlEncoded
    Flowable<Demo> postUser(@Field("username") String username, @Field("password") String password);

    //多个参数
    Flowable<Demo> postUser(@FieldMap Map<String, Object> map);

    /**
     * TODO DELETE
     */
    @DELETE("member_follow_member/{id}")
    Flowable<Demo> delete(@Header("Authorization") String auth, @Path("id") int id);

    /**
     * TODO PUT
     */
    @PUT("member")
    Flowable<Demo> put(@HeaderMap Map<String, String> headers,
                         @Query("nickname") String nickname);

    /**
     * TODO 文件上传
     */
    @Multipart
    @POST("upload")
    Flowable<ResponseBody> upload(@Part("description") RequestBody description, @Part MultipartBody.Part file);

    //亲测可用
    @Multipart
    @POST("member/avatar")
    Flowable<Demo> uploadImage(@HeaderMap Map<String, String> headers, @Part MultipartBody.Part file);

    /**
     * 多文件上传
     */
    @Multipart
    @POST("register")
    Flowable<ResponseBody> upload(@PartMap Map<String, RequestBody> params, @Part("description") RequestBody description);
    //Flowable<ResponseBody> upload(@Part() List<MultipartBody.Part> parts);

    @Multipart
    @POST("member/avatar")
    Flowable<Demo> uploadImage1(@HeaderMap Map<String, String> headers, @Part List<MultipartBody.Part> file);

    /**
     * 来自https://blog.csdn.net/impure/article/details/79658098
     * @Streaming 这个注解必须添加，否则文件全部写入内存，文件过大会造成内存溢出
     */
    @Streaming
    @GET
    Flowable<ResponseBody> download(@Header("RANGE") String start, @Url String url);
}
