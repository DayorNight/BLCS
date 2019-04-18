package blcs.lwb.lwbtool.utils;

import android.content.Context;
import android.util.Log;

import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import blcs.lwb.lwbtool.R;

/**
 * 添加网络证书
 */
public class LinSSLCertificate {
    private static final String TAG = "LinSSLCertificate";
    public static int keyServerStroreID = R.raw.fc;

    /**
     * 默认信任所有服务器
     */
    public  static  class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

//  sslSocketFactory(sslSocketFactory, trustManager)
    public static X509TrustManager createTrustAllManager() {
        X509TrustManager tm = null;
        try {
            tm  =  new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                    //do nothing，接受任意客户端证书
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                    //do nothing，接受任意服务端证书
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };
        } catch (Exception e) {

        }
        return tm;
    }


    /**
     *  默认信任所有的证书 最好加上证书认证，主流App都有自己的证书
     */
    public static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory sslSocketFactory = null;
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{createTrustAllManager()}, new SecureRandom());
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e) {

        }
        return sslSocketFactory;
    }

    /**
     * 指定添加证书
     */
    public static SSLSocketFactory createSSLSocketFactory(Context context) {
        SSLSocketFactory mSSLSocketFactory = null;
        if(mSSLSocketFactory==null){
            synchronized (LinSSLCertificate.class) {
                if(mSSLSocketFactory==null){
                    InputStream trustStream = context.getResources().openRawResource(keyServerStroreID);
                    SSLContext sslContext;
                    try {
                        sslContext = SSLContext.getInstance("TLS");
                    } catch (NoSuchAlgorithmException e) {
                        Log.e("httpDebug","createSingleSSLSocketFactory",e);
                        return null;
                    }
                    //获得服务器端证书
                    TrustManager[] turstManager = getTurstManager(trustStream);
                    //初始化ssl证书库
                    try {
                        sslContext.init(null,turstManager,new SecureRandom());
                    } catch (KeyManagementException e) {
                        Log.e("httpDebug","createSingleSSLSocketFactory",e);
                    }
                    //获得sslSocketFactory
                    mSSLSocketFactory=sslContext.getSocketFactory();
                }
            }
        }
        return mSSLSocketFactory;
    }

    /**获得指定流中的服务器端证书库*/

    public static TrustManager[] getTurstManager(InputStream... certificates) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null,null);
            int index = 0;
            for (InputStream certificate : certificates) {
                if (certificate == null) {
                    continue;
                }
                Certificate certificate1;
                try {
                    certificate1 = certificateFactory.generateCertificate(certificate);
                }finally {
                    certificate.close();
                }

                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias,certificate1);
            }

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory
                    .getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            return trustManagerFactory.getTrustManagers();

        } catch (Exception e) {
            Log.e("httpDebug","SSLSocketFactoryUtils",e);
        }

        X509TrustManager[] x509TrustManagers = {new MyX509TrustManager()};
        return x509TrustManagers;
    }

    public static class MyX509TrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) {
            Log.e(TAG,"cert: " + chain[0].toString() + ", authType: " + authType);
        }
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}
