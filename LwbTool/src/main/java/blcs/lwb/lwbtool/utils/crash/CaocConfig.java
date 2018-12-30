/*
 * Copyright 2014-2017 Eduard Ereza Martínez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package blcs.lwb.lwbtool.utils.crash;

import android.app.Activity;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Modifier;

public class CaocConfig implements Serializable {

    @IntDef({BACKGROUND_MODE_CRASH, BACKGROUND_MODE_SHOW_CUSTOM, BACKGROUND_MODE_SILENT})
    @Retention(RetentionPolicy.SOURCE)
    private @interface BackgroundMode {
        //I hate empty blocks
    }

    public static final int BACKGROUND_MODE_SILENT = 0;
    public static final int BACKGROUND_MODE_SHOW_CUSTOM = 1;
    public static final int BACKGROUND_MODE_CRASH = 2;

    private int backgroundMode = BACKGROUND_MODE_SHOW_CUSTOM;
    private boolean enabled = true;
    private boolean showErrorDetails = true;
    private boolean showRestartButton = true;
    private boolean logErrorOnRestart = true;
    private boolean trackActivities = false;
    private int minTimeBetweenCrashesMs = 3000;
    private Integer errorDrawable = null;
    private Class<? extends Activity> errorActivityClass = null;
    private Class<? extends Activity> restartActivityClass = null;
    private CustomActivityOnCrash.EventListener eventListener = null;

    @BackgroundMode
    public int getBackgroundMode() {
        return backgroundMode;
    }

    public void setBackgroundMode(@BackgroundMode int backgroundMode) {
        this.backgroundMode = backgroundMode;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isShowErrorDetails() {
        return showErrorDetails;
    }

    public void setShowErrorDetails(boolean showErrorDetails) {
        this.showErrorDetails = showErrorDetails;
    }

    public boolean isShowRestartButton() {
        return showRestartButton;
    }

    public void setShowRestartButton(boolean showRestartButton) {
        this.showRestartButton = showRestartButton;
    }

    public boolean isLogErrorOnRestart() {
        return logErrorOnRestart;
    }

    public void setLogErrorOnRestart(boolean logErrorOnRestart) {
        this.logErrorOnRestart = logErrorOnRestart;
    }

    public boolean isTrackActivities() {
        return trackActivities;
    }

    public void setTrackActivities(boolean trackActivities) {
        this.trackActivities = trackActivities;
    }

    public int getMinTimeBetweenCrashesMs() {
        return minTimeBetweenCrashesMs;
    }

    public void setMinTimeBetweenCrashesMs(int minTimeBetweenCrashesMs) {
        this.minTimeBetweenCrashesMs = minTimeBetweenCrashesMs;
    }

    @Nullable
    @DrawableRes
    public Integer getErrorDrawable() {
        return errorDrawable;
    }

    public void setErrorDrawable(@Nullable @DrawableRes Integer errorDrawable) {
        this.errorDrawable = errorDrawable;
    }

    @Nullable
    public Class<? extends Activity> getErrorActivityClass() {
        return errorActivityClass;
    }

    public void setErrorActivityClass(@Nullable Class<? extends Activity> errorActivityClass) {
        this.errorActivityClass = errorActivityClass;
    }

    @Nullable
    public Class<? extends Activity> getRestartActivityClass() {
        return restartActivityClass;
    }

    public void setRestartActivityClass(@Nullable Class<? extends Activity> restartActivityClass) {
        this.restartActivityClass = restartActivityClass;
    }

    @Nullable
    public CustomActivityOnCrash.EventListener getEventListener() {
        return eventListener;
    }

    public void setEventListener(@Nullable CustomActivityOnCrash.EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public static class Builder {
        private CaocConfig config;

        @NonNull
        public static Builder create() {
            Builder builder = new Builder();
            CaocConfig currentConfig = CustomActivityOnCrash.getConfig();
            CaocConfig config = new CaocConfig();
            config.backgroundMode = currentConfig.backgroundMode;
            config.enabled = currentConfig.enabled;
            config.showErrorDetails = currentConfig.showErrorDetails;
            config.showRestartButton = currentConfig.showRestartButton;
            config.logErrorOnRestart = currentConfig.logErrorOnRestart;
            config.trackActivities = currentConfig.trackActivities;
            config.minTimeBetweenCrashesMs = currentConfig.minTimeBetweenCrashesMs;
            config.errorDrawable = currentConfig.errorDrawable;
            config.errorActivityClass = currentConfig.errorActivityClass;
            config.restartActivityClass = currentConfig.restartActivityClass;
            config.eventListener = currentConfig.eventListener;
            builder.config = config;

            return builder;
        }

        /**
         * Defines if the error activity must be launched when the app is on background.
         * BackgroundMode.BACKGROUND_MODE_SHOW_CUSTOM: 当应用程序处于后台时崩溃，也会启动错误页面
         * BackgroundMode.BACKGROUND_MODE_CRASH: 当应用程序处于后台崩溃时显示默认系统错误
         * BackgroundMode.BACKGROUND_MODE_SILENT: 当应用程序处于后台时崩溃，默默地关闭程序
         * 默认模式：BackgroundMode.BACKGROUND_MODE_SHOW_CUSTOM
         */
        @NonNull
        public Builder backgroundMode(@BackgroundMode int backgroundMode) {
            config.backgroundMode = backgroundMode;
            return this;
        }

        /**
         * 这阻止了对崩溃的拦截,false表示阻止。用它来禁用customactivityoncrash框架
         */
        @NonNull
        public Builder enabled(boolean enabled) {
            config.enabled = enabled;
            return this;
        }

        /**
         * 这将隐藏错误活动中的“错误详细信息”按钮，从而隐藏堆栈跟踪。
         */
        @NonNull
        public Builder showErrorDetails(boolean showErrorDetails) {
            config.showErrorDetails = showErrorDetails;
            return this;
        }

        /**
         * Defines if the error activity should show a restart button.
         * Set it to true if you want to show a restart button,
         * false if you want to show a close button.
         * Note that even if restart is enabled but you app does not have any launcher activities,
         * a close button will still be used by the default error activity.
         * The default is true.
         */
        @NonNull
        public Builder showRestartButton(boolean showRestartButton) {
            config.showRestartButton = showRestartButton;
            return this;
        }

        /**
         * Defines if the stack trace must be logged again once the custom activity is shown.
         * Set it to true if you want to log the stack trace again,
         * false if you don't want the extra logging.
         * This option exists because the default Android Studio logcat view only shows the output
         * of the current process, and since the error activity runs on a new process,
         * you can't see the previous output easily.
         * Internally, it's logged when getConfigFromIntent() is called.
         * The default is true.
         */
        @NonNull
        public Builder logErrorOnRestart(boolean logErrorOnRestart) {
            config.logErrorOnRestart = logErrorOnRestart;
            return this;
        }

        /**
         * Defines if the activities visited by the user should be tracked
         * so they are reported when an error occurs.
         * The default is false.
         */
        @NonNull
        public Builder trackActivities(boolean trackActivities) {
            config.trackActivities = trackActivities;
            return this;
        }

        /**
         * Defines the time that must pass between app crashes to determine that we are not
         * in a crash loop. If a crash has occurred less that this time ago,
         * the error activity will not be launched and the system crash screen will be invoked.
         * The default is 3000.
         */
        @NonNull
        public Builder minTimeBetweenCrashesMs(int minTimeBetweenCrashesMs) {
            config.minTimeBetweenCrashesMs = minTimeBetweenCrashesMs;
            return this;
        }

        /**
         * Defines which drawable to use in the default error activity image.
         * Set this if you want to use an image other than the default one.
         */
        @NonNull
        public Builder errorDrawable(@Nullable @DrawableRes Integer errorDrawable) {
            config.errorDrawable = errorDrawable;
            return this;
        }

        /**
         * Sets the error activity class to launch when a crash occurs.
         * If null, the default error activity will be used.
         */
        @NonNull
        public Builder errorActivity(@Nullable Class<? extends Activity> errorActivityClass) {
            config.errorActivityClass = errorActivityClass;
            return this;
        }

        /**
         * Sets the main activity class that the error activity must launch when a crash occurs.
         * If not set or set to null, the default launch activity will be used.
         * If your app has no launch activities and this is not set, the default error activity will close instead.
         */
        @NonNull
        public Builder restartActivity(@Nullable Class<? extends Activity> restartActivityClass) {
            config.restartActivityClass = restartActivityClass;
            return this;
        }

        /**
         * Sets an event listener to be called when events occur, so they can be reported
         * by the app as, for example, Google Analytics events.
         * If not set or set to null, no events will be reported.
         *
         * @param eventListener The event listener.
         * @throws IllegalArgumentException if the eventListener is an inner or anonymous class
         */
        @NonNull
        public Builder eventListener(@Nullable CustomActivityOnCrash.EventListener eventListener) {
            if (eventListener != null && eventListener.getClass().getEnclosingClass() != null && !Modifier.isStatic(eventListener.getClass().getModifiers())) {
                throw new IllegalArgumentException("The event listener cannot be an inner or anonymous class, because it will need to be serialized. Change it to a class of its own, or make it a static inner class.");
            } else {
                config.eventListener = eventListener;
            }
            return this;
        }

        @NonNull
        public CaocConfig get() {
            return config;
        }

        public void apply() {
            CustomActivityOnCrash.setConfig(config);
        }
    }


}
