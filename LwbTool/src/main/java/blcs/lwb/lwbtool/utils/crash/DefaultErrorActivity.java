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

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import blcs.lwb.lwbtool.R;
import blcs.lwb.lwbtool.manager.AppManager;


/**
 * 异常显示界面
 */
public final class DefaultErrorActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_error_details;
    private String exception;

    @SuppressLint("PrivateResource")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        setContentView(R.layout.activity_default_error);
        Button restartButton = findViewById(R.id.btn_restart_app);
        restartButton.setOnClickListener(this);
        btn_error_details = findViewById(R.id.btn_error_details);
        btn_error_details.setOnClickListener(this);
        exception = getIntent().getStringExtra(CrashHandler.SP_BUG);
    }

    /**
     * 复制异常信息
     */
    private void copyErrorToClipboard() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        if (clipboard != null) {
            ClipData clip = ClipData.newPlainText(getString(R.string.error_information), exception);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(DefaultErrorActivity.this, getString(R.string.copy), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_restart_app) {
            AppManager.restartApp(this);
        } else if (i == R.id.btn_error_details) {
            initErrorDetails();

        }
    }
    /**
     * 异常信息
     */
    private void initErrorDetails() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.error_details)
                .setMessage(exception)
                .setPositiveButton(R.string.reStart, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppManager.restartApp(DefaultErrorActivity.this);
                    }
                })
                .setNeutralButton(R.string.copy,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                copyErrorToClipboard();
                            }
                        }).show();
        TextView textView = dialog.findViewById(android.R.id.message);
        if (textView != null) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.standard_work));
        }
    }
}
