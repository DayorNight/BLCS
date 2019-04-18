package blcs.lwb.lwbtool.utils;

import android.content.Context;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * WebView添加证书
 * 使用方式：
 * setWebViewClient(new LinSSLWebview(getActivity()))
 */
public class LinSSLWebview extends WebViewClient {
    private Context context;

    public LinSSLWebview(Context context) {
        this.context = context;
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        test12306(handler, view.getUrl());
    }

    // 以 12306 的证书为例，因为 12306 的证书是自签名的
    private void test12306(final SslErrorHandler handler, String url) {
        Request request = new Request.Builder().url(url).build();
        new OkHttpClient.Builder()
                .sslSocketFactory(LinSSLCertificate.createSSLSocketFactory(), LinSSLCertificate.createTrustAllManager())
                .build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("12306 error", e.getMessage());
                handler.cancel();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("12306 ", response.body().string());
                handler.proceed();
            }
        });
    }
}