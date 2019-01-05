package blcs.lwb.lwbtool.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLES20;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by lwb on 2018/6/4.
 */

public class OpenGLUitl {

    private static final String TAG = "OpenGLUitl";

    /**
     * 判断是否支持OpenGL2.0
     * @param context
     * @return
     */
    public static Boolean supportGLEs2(Context context){
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo deviceConfigurationInfo = activityManager.getDeviceConfigurationInfo();
        return deviceConfigurationInfo.reqGlEsVersion >= 0x20000;
    }

    /**
     * 从Raw获取字符串
     * @param context
     * @param code
     * @return
     */
    public static String getRawResource(Context context, int code){
        StringBuilder stringBuilder = new StringBuilder();
        try {
                InputStream inputStream = context.getResources().openRawResource(code);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String nextLine;
                while ((nextLine=bufferedReader.readLine())!=null){
                    stringBuilder.append(nextLine);
                    stringBuilder.append("\n");
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 把着色器代码上传到着色器当中
     * @param vertexCode
     * @return
     */
    public static int compileVertexShader(String vertexCode){
        return compileShader(GLES20.GL_VERTEX_SHADER,vertexCode);
    }

    public static int compileFragmentShader(String fragmentCode){
        return compileShader(GLES20.GL_FRAGMENT_SHADER, fragmentCode);
    }
    private static int compileShader(int type, String code) {
        int shaderId = GLES20.glCreateShader(type);
        if(shaderId==0){
            Log.e(TAG, "compileShader: ====shader===创建失败" );
            return 0;
        }
        GLES20.glShaderSource(shaderId,code);
        GLES20.glCompileShader(shaderId);
        final int[] compileShaderStatus=new int[1];
        GLES20.glGetShaderiv(shaderId, GLES20.GL_COMPILE_STATUS,compileShaderStatus,0);
        if (compileShaderStatus[0]==0){
            GLES20.glDeleteShader(shaderId);
            Log.e(TAG, "compileShader: ===shader===上传失败" );
            return 0;
        }
        return shaderId;
    }

    /**
     * 连接着色器
     * @param fragmentShader
     * @param vertexShader
     */
    public static int linkShader(int fragmentShader, int vertexShader) {
        int program = GLES20.glCreateProgram();
        if(program==0){
            Log.e(TAG, "linkShader: ====连接失败1" );
            return 0;
        }
        GLES20.glAttachShader(program,fragmentShader);
        GLES20.glAttachShader(program,vertexShader);
        GLES20.glLinkProgram(program);
        final int[] linkStatus=new int[1];
        GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS,linkStatus,0);
        if(linkStatus[0]==0){
            Log.e(TAG, "linkShader: =========连接失败2" );
            return 0;
        }
        return program;
    }
}
